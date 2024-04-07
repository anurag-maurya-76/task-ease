import { axiosClient } from "../config/axios";
import { Task } from "../interface/taskInterface";
import { errorHandler } from "./errorHandler";

class TaskMapService {
  async addTaskMap(payload: String) {
    const response = await axiosClient.post("/addTaskMap", {
      taskMapName: payload,
    });
    return errorHandler(response);
  }
  updateTask(payload: Task) {
    return new Promise((resolve) => {
      setTimeout(() => {
        resolve(true);
      }, 1000);
    });
  }
  async getTaskMap() {
    const response = await axiosClient.get("/getTaskMap");
    return errorHandler(response);
  }
}
export const taskMapService = new TaskMapService();
