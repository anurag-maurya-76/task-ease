import { axiosClient } from "../config/axios";
import { Filter } from "../interface/filterInterface";
import { Task } from "../interface/taskInterface";

class TaskService {
  async addTask(payload: Task) {
    const response = await axiosClient.post("/addTask", {
      taskName: payload.name,
      taskDescription: payload.description,
      taskStatus: payload.status,
      taskMapId: payload.taskMapId,
    });
    return response;
  }
  async updateTask(payload: Task) {
    const response = await axiosClient.post(`/updateTask/${payload.taskId}`, {
      taskName: payload.name,
      taskDescription: payload.description,
      taskStatus: payload.status,
    });
    return response;
  }
  async getTask(payload: Filter) {
    const response = await axiosClient.get(
      `/getTaskList/${payload.taskMapId}?sortBy=${payload.sortBy}&sortDir=${payload.sortDir}&searchBy=${payload.searchBy}&searchParameter=${payload.searchParamter}`
    );
    return response;
  }
}
export const taskService = new TaskService();
