import axios from "axios";
import { Buffer } from "buffer";
import { errorHandler } from "./errorHandler";

const loginClient = axios.create({
  baseURL: "http://localhost:8080",
  headers: {
    "Content-Type": "application/json",
    "X-Requested-With": "XMLHttpRequest",
  },
});
class LoginService {
  async login(payload: { email: string; password: string }) {
    try {
      const response = await loginClient.get(`/user`, {
        withCredentials: true,
        headers: {
          Authorization: `Basic ${Buffer.from(
            `${payload.email}:${payload.password}`
          ).toString("base64")}`,
        },
      });
      if (response.headers.authorization) {
        window.sessionStorage.setItem(
          "Authorization",
          response.headers.authorization
        );
      }
      return errorHandler(response);
    } catch (e) {
      return errorHandler(null);
    }
  }
  async register(payload: { email: string; password: string }) {
    try {
      const response = await loginClient
        .post(`/createUser`, {
          password: payload.password,
          email: payload.email,
        })
        .catch((error) => error);
      return errorHandler(response);
    } catch (e) {
      return errorHandler(null);
    }
  }
}
export const loginService = new LoginService();
