import { AxiosResponse } from "axios";
import toast from "react-hot-toast";

export const errorHandler = (response: AxiosResponse<any, any> | any) => {
  try {
    if (response.data.errorCode) {
      toast.error(response.data.errorMessage);
      return {
        error: true,
        message: response.data.errorMessage,
      };
    } else {
      return {
        error: false,
        responseBody: response.data.responseBody,
      };
    }
  } catch (e) {
    toast.error("We are facing some technical issue, please try again");
    return {
      error: true,
      message: "We are facing some technical issue, please try again",
    };
  }
};
