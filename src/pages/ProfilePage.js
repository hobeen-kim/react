import { Fragment } from "react";
import { ChangeUsername } from "../component/Profile/ChangeUsername";
import { ChangePassword } from "../component/Profile/ChangePassword";
import React from "react";

const ProfilePage = () => {
  return (
    <Fragment>
      <ChangePassword />
      <ChangeUsername />
    </Fragment>
  );
};

export default ProfilePage;