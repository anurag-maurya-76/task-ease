import { useEffect } from "react";
import { useQuery } from "react-query";
import { useDispatch, useSelector } from "react-redux";
import { taskMapService } from "../services/taskMapService";
import { filterAction } from "../state/slice/filterSlice";
import { taskAction } from "../state/slice/taskListSlice";
import { AppDispatch, RootState } from "../state/store";

export const useFetchTaskMap = () => {
  const dispatch = useDispatch<AppDispatch>();
  const filter = useSelector((state: RootState) => state.filter);
  const { data, isLoading } = useQuery<any>(
    ["getTask"],
    async () => await taskMapService.getTaskMap(),
    {
      cacheTime: 300000,
    }
  );

  useEffect(() => {
    if (isLoading === false && data?.error === false) {
      if (filter.taskMapId === "") {
        dispatch(
          filterAction.updateTaskMapId(data?.responseBody[0]?.taskMapId)
        );
      }
    }
  }, [data, dispatch, filter.taskMapId, isLoading]);

  useEffect(() => {
    if (isLoading) {
      dispatch(taskAction.updateTask([]));
    } else if (data) {
      dispatch(taskAction.updateTask([]));
    }
  }, [data, dispatch, isLoading]);

  return {
    data,
    isLoading,
  };
};
