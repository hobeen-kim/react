import '../../public/css/formation.css';
import { useState, useRef, useEffect } from 'react';
import FormationField from './Formation-field';
import { Button, Table, Form } from 'react-bootstrap';
import FormationColor from './FormationColor';

const Formation = () => {

    
    const [formation, setFormation] = useState("4-4-2");
    const [uniform, setUniform] = useState('plain');
    const [isVisibleMainColor, setIsVisibleMainColor] = useState(false); // 추가된 state
    const [isVisibleSubColor, setIsVisibleSubColor] = useState(false); // 추가된 state
    const [isVisibleTextColor, setIsVisibleTextColor] = useState(false); // 추가된 state
    const [isVisibleSubTextColor, setIsVisibleSubTextColor] = useState(false); // 추가된 state
    const [mainColor, setMainColor] = useState('red');
    const [subColor, setSubColor] = useState('white');
    const [textColor, setTextColor] = useState('white');
    const [subTextColor, setSubTextColor] = useState('white');


    useEffect(() => {
        // 이 함수는 모든 클릭 이벤트를 처리합니다.
        const handleClickOutside = (event) => {
            // 클릭한 요소가 'FormationColor' 컴포넌트가 아니라면 팝업을 닫습니다.
            if (event.target.closest('.formation-color-container') === null && event.target.closest('.formation-button') === null) {
                setIsVisibleMainColor(false);
                setIsVisibleSubColor(false);
                setIsVisibleTextColor(false);
                setIsVisibleSubTextColor(false);
            }
        };

        // 클릭 이벤트 리스너를 추가합니다.
        document.addEventListener('mousedown', handleClickOutside);

        // 컴포넌트가 언마운트될 때 클릭 이벤트 리스너를 제거합니다.
        return () => {
            document.removeEventListener('mousedown', handleClickOutside);
        };
    }, []); // 빈 의존성 배열을 전달하여 이 hook이 한 번만 실행되게 합니다.

    const handleFormationChange = (e) => {
        setFormation(e.target.value);
    }

    const handleUniformChange = (e) => {
        setUniform(e.target.value);
    }

    const handleMainColorButtonClick = () => { // 버튼을 클릭하면 isVisible state를 반전시키는 함수
        setIsVisibleMainColor(!isVisibleMainColor);
        setIsVisibleSubColor(false);
        setIsVisibleTextColor(false);
        setIsVisibleSubTextColor(false);
        
    }

    const handleSubColorButtonClick = () => { // 버튼을 클릭하면 isVisible state를 반전시키는 함수
        setIsVisibleSubColor(!isVisibleSubColor);
        setIsVisibleMainColor(false);
        setIsVisibleTextColor(false);
        setIsVisibleSubTextColor(false);
    }

    const handleTextColorButtonClick = () => { // 버튼을 클릭하면 isVisible state를 반전시키는 함수
        setIsVisibleTextColor(!isVisibleTextColor);
        setIsVisibleMainColor(false);
        setIsVisibleSubColor(false);
        setIsVisibleSubTextColor(false);
    }

    const handleSubTextColorButtonClick = () => { // 버튼을 클릭하면 isVisible state를 반전시키는 함수
        setIsVisibleSubTextColor(!isVisibleSubTextColor);
        setIsVisibleMainColor(false);
        setIsVisibleSubColor(false);
        setIsVisibleTextColor(false);
    }

    return (
        <div className='formation-container'>
            <div className='formation-info'>
                포메이션 인포
            </div>
            <div className='formation-row'>
                <div className='formation-console'>
                    포메이션<br/>
                    <Form.Select size="sm" style={{width:'15rem', marginBottom:'2rem'}} class='select-box-wide' defaultValue="4-4-2" onChange={handleFormationChange}>
                        <option value="4-4-2">4-4-2</option>
                        <option value="4-4-1-1">4-4-1-1</option>
                    </Form.Select>
                    <div>
                    유니폼 디자인<br/>
                    <Form.Select size="sm" style={{width:'15rem', marginBottom:'2rem'}} class='select-box-wide' defaultValue="plain" onChange={handleUniformChange}>
                        <option value="plain">plain</option>
                        <option value="stripes">stripes</option>
                    </Form.Select>
                    </div>
                    <div>
                    <Button className="formation-button" onClick={handleMainColorButtonClick}>유니폼 색깔 선택</Button>
                    {isVisibleMainColor && <FormationColor className="formation-color" onSelectColor={setMainColor} />} 
                    </div>
                    <div>
                    <Button className="formation-button" onClick={handleSubColorButtonClick}>유니폼 보조 색 선택</Button>
                    {isVisibleSubColor && <FormationColor className="formation-color" onSelectColor={setSubColor} />} 
                    </div>
                    <div>
                    <Button className="formation-button" onClick={handleTextColorButtonClick}>글자 색 선택</Button>
                    {isVisibleTextColor && <FormationColor className="formation-color" onSelectColor={setTextColor} />} 
                    </div>
                    <div>
                    <Button className="formation-button" onClick={handleSubTextColorButtonClick}>보조 글자 색 선택</Button>
                    {isVisibleSubTextColor && <FormationColor className="formation-color" onSelectColor={setSubTextColor} />} 
                    </div>

                </div>
                    <FormationField formation={formation} mainColor={mainColor} subColor={subColor} textColor={textColor} subTextColor={subTextColor} uniform={uniform}/>
            </div>
        </div>
    )
}

export default Formation;