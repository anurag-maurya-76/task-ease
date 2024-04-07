import { axiosClient } from "../config/axios";
import { Filter } from "../interface/filterInterface";
import { Task } from "../interface/taskInterface";
import { errorHandler } from "./errorHandler";

class TaskService {
  async addTask(payload: Task) {
    const response = await axiosClient.post("/addTask", {
      taskName: payload.name,
      taskDescription: payload.description,
      taskStatus: payload.status,
      taskMapId: payload.taskMapId,
    });
    return errorHandler(response);
  }
  async updateTask(payload: Task) {
    const response = await axiosClient.post(`/updateTask/${payload.taskId}`, {
      taskName: payload.name,
      taskDescription: payload.description,
      taskStatus: payload.status,
    });
    return errorHandler(response);
  }
  async getTaskList(payload: Filter) {
    const response = await axiosClient.get(
      `/getTaskList/${payload.taskMapId}?sortBy=${payload.sortBy}&sortDir=${payload.sortDir}&searchBy=${payload.searchBy}&searchParameter=${payload.searchParamter}`
    );
    return errorHandler(response);
  }
}
export const taskService = new TaskService();
