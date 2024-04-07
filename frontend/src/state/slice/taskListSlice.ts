import { PayloadAction, createSlice } from "@reduxjs/toolkit";
import { Task, TaskStatus } from "../../interface/taskInterface";

export interface AddTaskPayload {
  name: string;
  description: string;
  status: TaskStatus;
  date: string;
}

const initialState: Task[] = [];
const taskListSlice = createSlice({
  name: "taskList",
  initialState: initialState,
  reducers: {
    updateTask: (state, action: PayloadAction<Task[]>) => {
      state = action.payload;
      return state;
    },
  },
});
export const taskAction = taskListSlice.actions;
export const taskListReducer = taskListSlice.reducer;
