export type TaskStatus = "INPROGRESS" | "COMPLETED" | "BLOCKED" | "PENDING";

export interface Task {
  taskId: string;
  taskMapId: String;
  name: string;
  description: string;
  status: TaskStatus;
  date: number;
}
