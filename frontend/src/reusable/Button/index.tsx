import React, {
  ButtonHTMLAttributes,
  DetailedHTMLProps,
  ReactNode,
} from "react";
import styles from "./Button.module.scss";

interface ButtonProps {
  onClick?: () => {};
  type?: "button" | "submit" | "reset" | undefined;
  style?: DetailedHTMLProps<
    ButtonHTMLAttributes<HTMLButtonElement>,
    HTMLButtonElement
  >;
  className?: any;
  children?: ReactNode;
}

const Button = ({ onClick, type, style, className, children }: ButtonProps) => {
  return (
    <button
      className={`${className} ${styles.button}`}
      onClick={onClick}
      type={type}
      style={style}
    >
      {children}
    </button>
  );
};

export default Button;
