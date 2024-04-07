import styles from "./TaskCard.module.scss";
import { Task } from "../../interface/taskInterface";
import { MdModeEdit } from "react-icons/md";
import {
  IoIosCheckmarkCircleOutline,
  IoMdCloseCircleOutline,
} from "react-icons/io";
import { taskService } from "../../services/taskService";
import { queryClient } from "../../App";
import { useState } from "react";
import toast from "react-hot-toast";

const TaskCard = ({ task }: { task: Task }) => {
  const [mode, setMode] = useState("view");
  const [formState, setFormState] = useState({
    name: { value: "", error: "" },
    description: { value: "", error: "" },
  });
  const handleUpdateTask = async () => {
    const response = await taskService.updateTask({
      date: Date.now(),
      description: formState.description.value,
      name: formState.name.value,
      status: task.status,
      taskId: task.taskId,
      taskMapId: task.taskMapId,
    });
    if (response.error === false) {
      toast.success(response.responseBody);
      handleChangeMode();
      queryClient.invalidateQueries();
    }
  };
  const handleMarkCompleted = async () => {
    const response = await taskService.updateTask({
      date: Date.now(),
      description: task.description,
      name: task.name,
      status: "COMPLETED",
      taskId: task.taskId,
      taskMapId: task.taskMapId,
    });
    if (response.error === false) {
      toast.success(response.responseBody);
      queryClient.invalidateQueries();
    }
  };

  const handleChangeMode = () => {
    setMode((mode) => (mode === "view" ? "update" : "view"));
  };

  return (
    <div className={styles.taskCard}>
      <div className={styles.taskCard__header}>
        <div className={styles.taskCard__header__title}>
          {mode === "view" ? (
            task.name
          ) : (
            <div>
              <input
                className={styles.taskCard__input}
                value={formState.name.value}
                placeholder={"New Name"}
                onChange={(e) =>
                  setFormState((formState) => ({
                    ...formState,
                    name: {
                      value: e.target.value,
                      error:
                        e.target.value.length < 2
                          ? "Please enter valid name"
                          : "",
                    },
                  }))
                }
              />
              <div className={styles.taskCard__error}>
                {formState.name.error}
              </div>
            </div>
          )}
        </div>
        {task.status !== "COMPLETED" &&
          (mode === "view" ? (
            <MdModeEdit
              size={25}
              onClick={handleChangeMode}
              className={styles.taskCard__header__icon}
            />
          ) : (
            <div>
              <IoIosCheckmarkCircleOutline
                onClick={handleUpdateTask}
                size={30}
                className={styles.taskCard__header__icon}
              />
              <IoMdCloseCircleOutline
                size={30}
                onClick={handleChangeMode}
                className={styles.taskCard__header__icon}
              />
            </div>
          ))}
      </div>
      <div className={styles.taskCard__description}>
        {mode === "view" ? (
          task.description
        ) : (
          <div>
            <input
              className={styles.taskCard__input}
              value={formState.description.value}
              placeholder={"New Description"}
              onChange={(e) =>
                setFormState((formState) => ({
                  ...formState,
                  description: {
                    value: e.target.value,
                    error:
                      e.target.value.length < 2
                        ? "Please enter valid description"
                        : "",
                  },
                }))
              }
            />
            <div className={styles.taskCard__error}>
              {formState.description.error}
            </div>
          </div>
        )}
      </div>
      <div className={styles.taskCard__dueDate}>{task.date}</div>
      <div className={styles.taskCard__footer}>
        <div className={styles.taskCard__footer__status}>{task.status}</div>
        <div className={styles.taskCard__footer__actions}></div>
        {task.status !== "COMPLETED" && (
          <button
            type="button"
            className={styles.taskCard__button}
            onClick={handleMarkCompleted}
          >
            Mark as Completed
          </button>
        )}
      </div>
    </div>
  );
};

export default TaskCard;
