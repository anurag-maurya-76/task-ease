export type SortDir = "ASC" | "DESC";
export type SortBy =
  | "createdAt"
  | "taskName"
  | "taskDescription"
  | "taskStatus"
  | "";

export interface Filter {
  sortDir: SortDir;
  sortBy: SortBy;
  searchParamter: string;
  searchBy: "Date" | "";
  taskMapId: String;
}
