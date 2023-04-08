import React, { Fragment } from "react";
import MainNavigation from "./MainNavigation";

const Layout = (props) => {

  return (
    <Fragment>
 <div style={{position: 'fixed', width: '100%', zIndex: '10'}}>
        <MainNavigation/>
      </div>
      <main style={{marginTop: '64px'}}>{props.children}</main>    </Fragment>
  )
};

export default Layout;
