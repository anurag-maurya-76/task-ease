import { motion } from "framer-motion";
import React, { useState } from "react";
import styles from "./AddTask.module.scss";
import { IoMdClose } from "react-icons/io";
import formStyles from "../../form.module.scss";
import { taskService } from "../../services/taskService";
import { useSelector } from "react-redux";
import { RootState } from "../../state/store";
import { queryClient } from "../../App";
import toast from "react-hot-toast";
const initialFormState = {
  name: {
    value: "",
    error: "",
  },
  desc: {
    value: "",
    error: "",
  },
};
const AddTask = () => {
  const [formState, setFormState] = useState(initialFormState);

  const filter = useSelector((state: RootState) => state.filter);
  const handleFormDispatch = (payload: Object) => {
    setFormState((formState) => ({
      ...formState,
      ...payload,
    }));
  };

  const onChange = {
    name: (e: React.ChangeEvent<HTMLInputElement>) => {
      handleFormDispatch({
        name: {
          value: e.target.value,
          error:
            e.target.value.length < 2 ? "Please enter valid task name" : "",
        },
      });
    },
    desc: (e: React.ChangeEvent<HTMLInputElement>) => {
      handleFormDispatch({
        desc: {
          value: e.target.value,
          error:
            e.target.value.length < 2
              ? "Please enter valid task description"
              : "",
        },
      });
    },
  };

  const handleSubmit = async () => {
    if (formState.name.value.length < 2) {
      handleFormDispatch({
        name: {
          value: formState.name.value,
          error:
            formState.name.value.length < 2
              ? "Please enter valid task name"
              : "",
        },
      });
      return;
    }
    if (formState.name.value.length < 2) {
      handleFormDispatch({
        desc: {
          value: formState.desc.value,
          error:
            formState.desc.value.length < 2
              ? "Please enter valid task description"
              : "",
        },
      });
      return;
    }
    const response = await taskService.addTask({
      name: formState.name.value,
      description: formState.desc.value,
      date: Date.now(),
      status: "PENDING",
      taskId: "Wow",
      taskMapId: filter.taskMapId,
    });

    if (response.error === false) {
      toast.success(response.responseBody);
      queryClient.invalidateQueries();
      handleFormDispatch(initialFormState);
      setOpen(false);
    }
  };

  const [open, setOpen] = useState(false);
  return (
    <motion.div
      className={styles.addTask}
      onClick={() => !open && setOpen(true)}
      style={{ cursor: open ? "" : "pointer" }}
      initial={{
        height: 50,
        width: 150,
      }}
      animate={{
        height: open ? 300 : 50,
        width: open ? "100%" : 150,
      }}
    >
      {open ? (
        <motion.div
          key={1}
          initial={{ opacity: 0 }}
          animate={{ opacity: 1 }}
          exit={{ opacity: 0 }}
        >
          <IoMdClose
            size={28}
            onClick={() => setOpen(false)}
            color="white"
            className={styles.icon}
          />
          <form className={formStyles.form}>
            <label htmlFor="taskName">Task Name</label>
            <input
              id={"taskName"}
              type="text"
              value={formState.name.value}
              onChange={onChange.name}
            />
            <div className={styles.error}>{formState.name.error}</div>
            <label htmlFor="taskDesc">Task Description</label>
            <input
              id={"taskDesc"}
              type="text"
              value={formState.desc.value}
              onChange={onChange.desc}
            />
            <div className={styles.error}>{formState.desc.error}</div>
            <button
              type="button"
              className={styles.submitBtn}
              onClick={handleSubmit}
            >
              Add Task
            </button>
          </form>
        </motion.div>
      ) : (
        <motion.div key={2} exit={{ opacity: 0 }}>
          Add task
        </motion.div>
      )}
    </motion.div>
  );
};

export default AddTask;
