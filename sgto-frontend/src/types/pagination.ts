export interface ResponseAPI<T> {
    success: boolean;
    message?: string;
    data: T;
}

export interface Pagination<T> {
    content: T[];
    totalElements: number;
    page: number;
    size: number;
    totalPages: number;
    last: boolean;
}