import axios from "axios";
import { Buffer } from "buffer";

const loginClient = axios.create({
  baseURL: "http://localhost:8080",
  headers: {
    "Content-Type": "application/json",
    "X-Requested-With": "XMLHttpRequest",
  },
});
class LoginService {
  async login(payload: { email: string; password: string }) {
    const base64encodedData = Buffer.from(
      `${payload.email}:${payload.password}`
    ).toString("base64");
    const response = await loginClient.get(`/user`, {
      withCredentials: true,
      headers: {
        Authorization: `Basic ${base64encodedData}`,
      },
    });
    if (response.headers.authorization) {
      window.sessionStorage.setItem(
        "Authorization",
        response.headers.authorization
      );
    }
    return response;
  }
  register(payload: { email: string; password: string }) {
    const response = loginClient.post(`/createUser`, {
      password: payload.password,
      email: payload.email,
    });
    return response;
  }
}
export const loginService = new LoginService();
