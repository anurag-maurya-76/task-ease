import { useEffect, useState } from "react";
import { useQuery } from "react-query";
import { useSelector } from "react-redux";
import { Task } from "../interface/taskInterface";
import { taskService } from "../services/taskService";
import { RootState } from "../state/store";

export const useFetchTaskList = () => {
  const filter = useSelector((state: RootState) => state.filter);
  const [taskList, setTaskList] = useState<Task[]>([]);
  const { data, isLoading } = useQuery<any>(
    ["getTask", filter],
    async () => await taskService.getTask(filter),
    {
      cacheTime: 300000,
    }
  );

  useEffect(() => {
    if (data) {
      const taskList: Task[] = [];
      data?.data?.responseBody?.map((value: any, key: number) => {
        const task: Task = {
          description: value.taskDescription,
          name: value.taskName,
          status: value.taskStatus,
          taskMapId: filter.taskMapId,
          taskId: value.taskId,
          date: value.createdAt,
        };
        taskList.push(task);
        return true;
      });
      setTaskList(taskList);
    }
  }, [data, filter.taskMapId]);

  return {
    taskList,
    isLoading,
  };
};
