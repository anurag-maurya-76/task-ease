import { axiosClient } from "../config/axios";
import { Task } from "../interface/taskInterface";

class TaskMapService {
  async addTaskMap(payload: String) {
    const response = await axiosClient.post("/addTaskMap", {
      taskMapName: payload,
    });
    return response;
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
    return response;
  }
}
export const taskMapService = new TaskMapService();
