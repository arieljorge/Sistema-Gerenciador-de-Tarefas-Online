package com.github.arieljorge.sgto.service.impl;

import com.github.arieljorge.sgto.dto.TarefaCreateDto;
import com.github.arieljorge.sgto.dto.TarefaDto;
import com.github.arieljorge.sgto.dto.TarefaEditDto;
import com.github.arieljorge.sgto.dto.TarefaSimpleDto;
import com.github.arieljorge.sgto.entity.Tarefa;
import com.github.arieljorge.sgto.entity.TarefaUsuarios;
import com.github.arieljorge.sgto.entity.TarefaUsuariosID;
import com.github.arieljorge.sgto.entity.Usuario;
import com.github.arieljorge.sgto.repository.TarefaRepository;
import com.github.arieljorge.sgto.repository.TarefaUsuariosRepository;
import com.github.arieljorge.sgto.repository.UsuarioRepository;
import com.github.arieljorge.sgto.service.TarefaService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TarefaServiceImpl implements TarefaService {

    private final UsuarioRepository usuarioRepository;
    private final TarefaRepository tarefaRepository;
    private final TarefaUsuariosRepository tarefaUsuariosRepository;

    @Override
    public List<TarefaSimpleDto> obterTarefas(Short idQuadro) {
        List<Tarefa> tarefas = this.tarefaRepository.findAllByIdQuadro(idQuadro, Sort.by(Sort.Direction.DESC, "id"));
        return tarefas.stream().map(TarefaSimpleDto::new).toList();
    }

    @Override
    public TarefaDto obterTarefa(Integer idTarefa) {
        Tarefa tarefa = this.tarefaRepository.findById(idTarefa).orElseThrow(() -> {
            return new RuntimeException("tarefa não encontrada");
        });

        List<TarefaUsuarios> tarefaUsuarios = this.tarefaUsuariosRepository.findAllById_IdTarefa(idTarefa);
        List<UUID> usuarioIds = Optional
                .ofNullable(tarefaUsuarios)
                .map(listaTarefaUsuarios -> listaTarefaUsuarios.stream().map(v -> v.getId().getIdUsuario()).toList())
                .orElse(null);

        List<Usuario> usuarios = Optional
                .ofNullable(usuarioIds)
                .map(this.usuarioRepository::findAllById)
                .orElse(new ArrayList<>());

        return new TarefaDto(tarefa, usuarios);
    }

    @Override
    @Transactional
    public TarefaDto cadastrarTarefa(TarefaCreateDto tarefaCreateDto) {
        final Tarefa novaTarefa = Tarefa
                .builder()
                .idQuadro(tarefaCreateDto.idQuadro())
                .titulo(tarefaCreateDto.titulo())
                .descricao(tarefaCreateDto.descricao())
                .prazo(Optional.ofNullable(tarefaCreateDto.prazo()).map(LocalDate::atStartOfDay).orElse(null))
                .criadoEm(LocalDateTime.now())
                .build();

        final Tarefa tarefa = this.tarefaRepository.save(novaTarefa);

        List<TarefaUsuarios> tarefaUsuarios = Optional
                .ofNullable(tarefaCreateDto.usuarios())
                .map(usuarios -> usuarios.stream().map(v -> {
                    final var tarefaUsuariosID = new TarefaUsuariosID(tarefa.getId(), v);
                    return new TarefaUsuarios(tarefaUsuariosID);
                }).toList())
                .orElse(null);

        tarefaUsuarios = Optional
                .ofNullable(tarefaUsuarios)
                .map(this.tarefaUsuariosRepository::saveAll)
                .orElse(new ArrayList<>());

        List<UUID> usuarioIds = tarefaUsuarios.stream().map(v -> v.getId().getIdUsuario()).toList();
        List<Usuario> usuarios = this.usuarioRepository.findAllById(usuarioIds);

        return new TarefaDto(tarefa, usuarios);
    }

    @Override
    public TarefaDto atualizarTarefa(TarefaEditDto tarefaEditDto) {
        Tarefa tarefa = this.tarefaRepository.findById(tarefaEditDto.id()).orElseThrow(() -> {
            return new RuntimeException("tarefa não encontrada");
        });

        tarefa.setTitulo(tarefaEditDto.titulo());
        tarefa.setDescricao(tarefaEditDto.descricao());
        tarefa.setPrazo(Optional.ofNullable(tarefaEditDto.prazo()).map(LocalDate::atStartOfDay).orElse(null));
        tarefa.setIdQuadro(tarefaEditDto.idQuadro());

        this.tarefaUsuariosRepository.deleteAllById_IdTarefa(tarefa.getId());
        final Tarefa tarefaEditada = this.tarefaRepository.save(tarefa);

        List<TarefaUsuarios> tarefaUsuarios = Optional
                .ofNullable(tarefaEditDto.usuarios())
                .map(usuarios -> usuarios.stream().map(v -> {
                    final var tarefaUsuariosID = new TarefaUsuariosID(tarefaEditada.getId(), v);
                    return new TarefaUsuarios(tarefaUsuariosID);
                }).toList())
                .orElse(null);

        tarefaUsuarios = Optional
                .ofNullable(tarefaUsuarios)
                .map(this.tarefaUsuariosRepository::saveAll)
                .orElse(new ArrayList<>());

        List<UUID> usuarioIds = tarefaUsuarios.stream().map(v -> v.getId().getIdUsuario()).toList();
        List<Usuario> usuarios = this.usuarioRepository.findAllById(usuarioIds);

        return new TarefaDto(tarefaEditada, usuarios);
    }

    @Override
    @Transactional
    public void removerTarefa(Integer idTarefa) {
        this.tarefaUsuariosRepository.deleteAllById_IdTarefa(idTarefa);
        this.tarefaRepository.deleteAllById(idTarefa);
    }
}
