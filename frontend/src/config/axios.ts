import axios from "axios";
import Cookies from "js-cookie";

export const axiosClient = axios.create({
  baseURL: "http://localhost:8080",
  // withCredentials: true,
  headers: {
    "Content-Type": "application/json",
    "X-Requested-With": "XMLHttpRequest",
  },
});

axiosClient.interceptors.request.use(
  (config) => {
    const token = sessionStorage.getItem("Authorization");
    const xsrfToken = Cookies.get("XSRF-TOKEN");
    if (token) {
      config.headers["Authorization"] = `${token}`;
    }
    if (xsrfToken) {
      config.headers["X-XSRF-TOKEN"] = `${xsrfToken}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);
