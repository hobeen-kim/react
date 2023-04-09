import { Fragment } from "react";
import PlayerList from "../component/player/PlayerList";
import React from "react";
import {PlayerContextProvider} from "../store/player/player-context";

const PlayerPage = () => {

  return (
    <Fragment>
        <div className="player-background">
        <PlayerContextProvider>
            <PlayerList />
        </PlayerContextProvider>
        </div>
    </Fragment>
  );
};

export default PlayerPage;