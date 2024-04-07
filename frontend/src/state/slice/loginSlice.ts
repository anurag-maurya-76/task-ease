import { createSlice } from "@reduxjs/toolkit";
import { LoginInfo } from "../../interface/loginInterface";

export const initialState: LoginInfo = {
  name: "",
  userId: "",
};
export const loginSlice = createSlice({
  name: "login",
  reducers: {},
  initialState: initialState,
});
export const loginReducer = loginSlice.reducer;
