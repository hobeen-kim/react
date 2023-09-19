import { Table } from 'react-bootstrap';


const PlayerRecord = ({selectedPlayer, recordNameInteg}) => {

    return (
        <>
        {selectedPlayer && selectedPlayer.recordResponseDto?.length === 0 && <div div class="content-title">There are no records.</div>}
        {selectedPlayer && selectedPlayer.recordResponseDto?.length > 0 &&(
      <div className="playerlist-record-table">
        <div className="content-title">{selectedPlayer.name} 선수의 경기 기록</div>
        <Table striped bordered hover variant="dark" >
          <thead>
            <tr>
              <th>참여 경기</th>
              <th>일자</th>
              <th>경기 포지션</th>
              {recordNameInteg.map((name) => (
              <th key={name}>{name}</th>))}
            </tr>
          </thead>
          <tbody>
            {selectedPlayer.recordResponseDto.map((record) => (
              <tr key={record.id}>
                <td>{record.gameName}</td>
                <td>{record.createdAt}</td>
                <td>{record.gamePosition}
                    {record.timeIn ? <><br/>({record.timeIn}분 <span style={{color: 'green'}}>in</span>)</> : null}
                    {record.timeOut ? <><br/>({record.timeOut}분 <span style={{color: 'red'}}>out</span>)</> : null}
                </td>
                <td>{record.touch}</td>
                <td>{record.goal}</td>
                <td>{record.assist}</td>
                <td>{record.chanceMaking}</td>
                <td>{`${record.shoot} (${record.validShoot})`}</td>
                <td>{`${record.dribble} (${record.successDribble})`}</td>
                <td>{`${record.pass} (${record.successPass})`}</td>
                <td>{`${record.longPass} (${record.successLongPass})`}</td>
                <td>{`${record.crossPass} (${record.successCrossPass})`}</td>
                <td>{record.tackle}</td>
                <td>{record.intercept}</td>
                <td>{`${record.contention} (${record.successContention})`}</td>
                <td>{record.turnover}</td>
              </tr>
            ))}
          </tbody>
        </Table>
      </div>
    )}
    </>
    );
}

export default PlayerRecord;