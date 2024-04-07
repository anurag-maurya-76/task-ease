import { configureStore } from "@reduxjs/toolkit";
import { filterReducer } from "./slice/filterSlice";
import { loginReducer } from "./slice/loginSlice";
import { taskListReducer } from "./slice/taskListSlice";

export const store = configureStore({
  reducer: {
    taskList: taskListReducer,
    filter: filterReducer,
    login: loginReducer,
  },
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
