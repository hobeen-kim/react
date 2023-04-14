import {SectionsContainer, Section} from 'react-fullpage';
import Scrolldown from '../../public/image/scrollDown.png';
import '../../public/css/logginedPage.css'
import Guide1 from '../../public/gif/guide1.gif';
import Guide2 from '../../public/gif/guide2.gif';
import Guide3 from '../../public/gif/guide3.gif';
import Guide4 from '../../public/gif/guide4.gif';
import PointerImg from '../../public/image/pointer.png'
import { useNavigate } from 'react-router-dom';



const Logined = () => {

    const navigate = useNavigate();
    let options = {
        anchors: ['sectionOne', 'sectionTwo', 'sectionThree', 'sectionFour', 'sectionFive', 'sectionSix', 'sctionSeven'],
      };
      return (
        <SectionsContainer {...options}>
            <Section>
              <div className='loggined-section'>환영합니다!</div>
                <div className='loggined-div'>
                  <div className='content'>설명 보기</div>
                  <img className='loggined-img'src={Scrolldown} alt="scrollDown"/>
                </div>
            </Section>
            <Section>
            <h1 className='loggined-sectionIn'>선수 관리 페이지</h1>
            <div className='flex-container'>
              <img className='img-gif' src={Guide1} alt="guide1"/>
              <div className='loggined-content'>
              <br/>
              <h2>팀의 선수를 관리해주세요!</h2><br/>
                선수를 추가, 수정, 삭제할 수 있습니다.<br/><br/>
                선수 이름을 누르면 <br/><br/>참여했던 경기의 기록을 볼 수 있습니다.<br/>
              </div>
            </div>
            <div className='loggined-flex-container'>
              <img className='loggined-img loggined-div'src={Scrolldown} alt="scrollDown"/>
            </div>
            </Section>
            <Section>
            <h1 className='loggined-sectionIn'>경기 관리 페이지</h1>
            <div className='flex-container'>
              <img className='img-gif' src={Guide2} alt="guide2"/>
              <div className='loggined-content'>
              <br/>
                <br/>
                <h2>경기 생성</h2><br/>
                경기를 생성합니다.<br/><br/>
                포지션별 선수는 없어도 되지만, 선수 중복은 불가능합니다!<br/><br/>
                생성했는데 보이지 않는다면 새로고침 해주세요.<br/><br/>

                <h2>경기 / 선수기록 수정</h2><br/>
                경기 및 선수 기록을 수정합니다.<br/><br/>
                교체 시간&#40;time In, Out&#41;을 설정하면 선수 이름 아래에 표시됩니다!<br/><br/>
                포지션에 따라 테이블 색이 달라집니다.<br/><br/>
                <br/>
              </div>
            </div>
            <div className='loggined-flex-container'>
              <img className='loggined-img loggined-div'src={Scrolldown} alt="scrollDown"/>
            </div>
            </Section>
            <Section>
            <h1 className='loggined-sectionIn'>히트맵 툴바</h1>
            <div className='flex-container'>
              <img className='img-gif' src={Guide3} alt="guide3"/>
              <div className='loggined-content'>
              <br/>
                <br/>
                <h2>히트맵에 선수 정보를 저장하고 싶으면?</h2><br/>
                클릭 전 저장할 선수를 우측 배너에서 선택하세요<br/><br/>
                시간을 설정하면 히트맵에 시간이 표시됩니다! &#40;단축키 : A, S&#41;<br/><br/>

                <h2>"저장하기 귀찮아요!"</h2><br/>
                선수 정보를 저장하지 않으셔도 됩니다!<br/><br/>
                선수를 '무명' 으로 선택하고 히트맵을 만들어주세요.<br/><br/>
                <br/>
              </div>
              <div className='loggined-content' style={{marginLeft:'200px', textAlign:"left"}}>
              <br/><br/>
                <h2>주요 단축키</h2><br/>
                시간 1분 더하기 : S<br/><br/>
                시간 1분 빼기 : A<br/><br/>
                상대팀 선택 : `<br/><br/>
                선수 선택 : 1~0, Q~P<br/><br/>
              </div>
            </div>
            <div className='loggined-flex-container'>
              <img className='loggined-img loggined-div'src={Scrolldown} alt="scrollDown"/>
            </div>
            </Section>
            <Section>
            <h1 className='loggined-sectionIn'>히트맵 만들기</h1>
            <div className='flex-container'>
              <img className='img-gif' src={Guide4} alt="guide4"/>
              <div className='loggined-content'>
                <br/><br/>
                <h2>슈팅 기록</h2><br/>
                슈팅은 유효슛과 슛으로 구분됩니다.<br/><br/>
                슈팅 설정 시 Ctrl 혹은 Ctrl, Alt 를 누른 채로 클릭하시면 됩니다.<br/><br/>
                드래그 할 필요는 없어요!<br/><br/>

                <h2>Ctrl + Shift ??</h2><br/>
                몇 가지 이슈&#40;주로 지능 이슈&#41;로 Ctrl + Shift + Z 대신 <br/><br/>
                Ctrl + Shift 를 사용해 주세요.<br/><br/>
                <br/>
              </div>
              <div className='loggined-content' style={{marginLeft:'200px', textAlign:"left"}}>
              <br/><br/>
                <h2>주요 단축키</h2><br/>
                슈팅 : Ctrl + 클릭 - 클릭<br/><br/>
                유효슛 : Ctrl + Alt + 클릭 - 클릭<br/><br/>
                정보보기 : z<br/><br/>
                선택삭제 : Z + 클릭<br/><br/>
                이전상태 : Ctrl + Z<br/><br/>
                되돌리기 : Ctrl + Shift<br/><br/>
              </div>
            </div>
            <div className='loggined-flex-container'>
              <img className='loggined-img loggined-div'src={Scrolldown} alt="scrollDown"/>
            </div>
            </Section>
            <Section>
              <div className='loggined-section'>
                <h1 >이제 팀 관리를 시작해보세요!</h1>
                <br/>
                <img className='pointer-img' src={PointerImg} alt="pointer"/>
                <button className='loggined-button' onClick={()=>navigate('/players')}>선수 관리</button>
                <button className='loggined-button' onClick={()=>navigate('/games')}>경기 관리</button>
                <img className='pointer-img-reverse' src={PointerImg} alt="pointer"/>
                <br/><br/>
              </div>
              <div className='loggined-div'>
                  <div><h2>"뭔가 이상한데요?"</h2></div>
                  <img className='loggined-img'src={Scrolldown} alt="scrollDown"/>
                </div>
            </Section>
            <Section>
              <div className='loggined-section'>
                <h1 >"뭔가 제대로 작동되지 않는데요?"</h1>
                <br/><br/>
                <h2>안심하세요! 대부분 버그입니다.</h2><br/>
                <br/><br/>
                <h2>버그 제보 : sksjsksh32@naver.com</h2><br/>
                
                <h2>감사합니다.</h2><br/>
                <h4>참고 : 개발자가 아직 ID, PW 찾기 기능을 구현하지 않았으니 까먹지 마세요!</h4>
                <br/><br/>
              </div>
            </Section>
        </SectionsContainer>
      );
}

export default Logined;