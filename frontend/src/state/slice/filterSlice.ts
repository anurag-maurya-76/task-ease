import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { Filter, SortBy } from "../../interface/filterInterface";

export interface Search {
  searchParameter: string;
  searchBy: "Date" | "";
}

export const initialState: Filter = {
  sortDir: "ASC",
  sortBy: "",
  searchParamter: "",
  searchBy: "",
  taskMapId: "",
};

export const filterSlice = createSlice({
  name: "filter",
  initialState: initialState,
  reducers: {
    updateTaskMapId: (state, taskMapId: PayloadAction<String>) => {
      state = {
        ...state,
        taskMapId: taskMapId.payload,
      };
      return state;
    },
    updateSortDir: (state) => {
      state = {
        ...state,
        sortDir: state.sortDir === "ASC" ? "DESC" : "ASC",
      };
      return state;
    },
    updateSortBy: (state, filter: PayloadAction<SortBy>) => {
      state = {
        ...state,
        sortBy: filter.payload,
      };
      return state;
    },
    updateSearch: (state, filter: PayloadAction<Search>) => {
      state = {
        ...state,
        searchBy: filter.payload.searchBy,
        searchParamter: filter.payload.searchParameter,
      };
      return state;
    },
    resetFilter: (state) => {
      return {
        ...state,
        sortDir: "ASC",
        sortBy: "",
        searchParamter: "",
        searchBy: "",
      };
    },
  },
});
export const filterAction = filterSlice.actions;
export const filterReducer = filterSlice.reducer;
