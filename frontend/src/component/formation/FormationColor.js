import { Button, Table, Form } from 'react-bootstrap';

const FormationColor = ({ onSelectColor }) => {

    const handleColorClick = (color) => {
        onSelectColor(color); 
    }

    return (
        <div className="formation-color-container">
            <div>
                <Button className='formation-color-button' style={{backgroundColor:"rgb(77, 77, 77)"}} onClick={() => handleColorClick("rgb(77, 77, 77)")}></Button>
                <Button className='formation-color-button' style={{backgroundColor:"rgb(153, 153, 153)"}} onClick={() => handleColorClick("rgb(153, 153, 153)")}></Button>
                <Button className='formation-color-button' style={{backgroundColor:"rgb(255, 255, 255)", border:"solid 1px rgb(200,200,200)"}} onClick={() => handleColorClick("rgb(255, 255, 255)")}></Button>
                <Button className='formation-color-button' style={{backgroundColor:"rgb(244, 78, 59)"}} onClick={() => handleColorClick("rgb(244, 78, 59)")}></Button>
                <Button className='formation-color-button' style={{backgroundColor:"rgb(254, 146, 0)"}} onClick={() => handleColorClick("rgb(254, 146, 0)")}></Button>
                <Button className='formation-color-button' style={{backgroundColor:"rgb(252, 220, 0)"}} onClick={() => handleColorClick("rgb(252, 220, 0)")}></Button>
                <Button className='formation-color-button' style={{backgroundColor:"rgb(219, 223, 0)"}} onClick={() => handleColorClick("rgb(219, 223, 0)")}></Button>
                <Button className='formation-color-button' style={{backgroundColor:"rgb(164, 221, 0)"}} onClick={() => handleColorClick("rgb(164, 221, 0)")}></Button>
                <Button className='formation-color-button' style={{backgroundColor:"rgb(104, 204, 202)"}} onClick={() => handleColorClick("rgb(104, 204, 202)")}></Button>
                <Button className='formation-color-button' style={{backgroundColor:"rgb(115, 216, 255)"}} onClick={() => handleColorClick("rgb(115, 216, 255)")}></Button>
                <Button className='formation-color-button' style={{backgroundColor:"rgb(174, 161, 255)"}} onClick={() => handleColorClick("rgb(174, 161, 255)")}></Button>
                <Button className='formation-color-button' style={{backgroundColor:"rgb(253, 161, 255)"}} onClick={() => handleColorClick("rgb(253, 161, 255)")}></Button>
            </div>
            <div>
                <Button className='formation-color-button' style={{backgroundColor:"rgb(51, 51, 51)"}} onClick={() => handleColorClick("rgb(51, 51, 51)")}></Button>
                <Button className='formation-color-button' style={{backgroundColor:"rgb(128, 128, 128)"}} onClick={() => handleColorClick("rgb(128, 128, 128)")}></Button>
                <Button className='formation-color-button' style={{backgroundColor:"rgb(204, 204, 204)"}} onClick={() => handleColorClick("rgb(204, 204, 204)")}></Button>
                <Button className='formation-color-button' style={{backgroundColor:"rgb(211, 49, 21)"}} onClick={() => handleColorClick("rgb(211, 49, 21)")}></Button>
                <Button className='formation-color-button' style={{backgroundColor:"rgb(226, 115, 0)"}} onClick={() => handleColorClick("rgb(226, 115, 0)")}></Button>
                <Button className='formation-color-button' style={{backgroundColor:"rgb(252, 196, 0)"}} onClick={() => handleColorClick("rgb(252, 196, 0)")}></Button>
                <Button className='formation-color-button' style={{backgroundColor:"rgb(176, 188, 0)"}} onClick={() => handleColorClick("rgb(176, 188, 0)")}></Button>
                <Button className='formation-color-button' style={{backgroundColor:"rgb(104, 188, 0)"}} onClick={() => handleColorClick("rgb(104, 188, 0)")}></Button>
                <Button className='formation-color-button' style={{backgroundColor:"rgb(22, 165, 165)"}} onClick={() => handleColorClick("rgb(22, 165, 165)")}></Button>
                <Button className='formation-color-button' style={{backgroundColor:"rgb(0, 156, 224)"}} onClick={() => handleColorClick("rgb(0, 156, 224)")}></Button>
                <Button className='formation-color-button' style={{backgroundColor:"rgb(123, 100, 255)"}} onClick={() => handleColorClick("rgb(123, 100, 255)")}></Button>
                <Button className='formation-color-button' style={{backgroundColor:"rgb(250, 40, 255)"}} onClick={() => handleColorClick("rgb(250, 40, 255)")}></Button>
            </div>
            <div>
                <Button className='formation-color-button' style={{backgroundColor:"rgb(0, 0, 0)"}} onClick={() => handleColorClick("rgb(0, 0, 0)")}></Button>
                <Button className='formation-color-button' style={{backgroundColor:"rgb(102, 102, 102)"}} onClick={() => handleColorClick("rgb(102, 102, 102)")}></Button>
                <Button className='formation-color-button' style={{backgroundColor:"rgb(179, 179, 179)"}} onClick={() => handleColorClick("rgb(179, 179, 179)")}></Button>
                <Button className='formation-color-button' style={{backgroundColor:"rgb(159, 5, 0)"}} onClick={() => handleColorClick("rgb(159, 5, 0)")}></Button>
                <Button className='formation-color-button' style={{backgroundColor:"rgb(196, 81, 0)"}} onClick={() => handleColorClick("rgb(196, 81, 0)")}></Button>
                <Button className='formation-color-button' style={{backgroundColor:"rgb(251, 158, 0)"}} onClick={() => handleColorClick("rgb(251, 158, 0)")}></Button>
                <Button className='formation-color-button' style={{backgroundColor:"rgb(128, 137, 0)"}} onClick={() => handleColorClick("rgb(128, 137, 0)")}></Button>
                <Button className='formation-color-button' style={{backgroundColor:"rgb(25, 77, 51)"}} onClick={() => handleColorClick("rgb(25, 77, 51)")}></Button>
                <Button className='formation-color-button' style={{backgroundColor:"rgb(12, 131, 125)"}} onClick={() => handleColorClick("rgb(12, 131, 125)")}></Button>
                <Button className='formation-color-button' style={{backgroundColor:"rgb(0, 98, 177)"}} onClick={() => handleColorClick("rgb(0, 98, 177)")}></Button>
                <Button className='formation-color-button' style={{backgroundColor:"rgb(101, 50, 148)"}} onClick={() => handleColorClick("rgb(101, 50, 148)")}></Button>
                <Button className='formation-color-button' style={{backgroundColor:"rgb(171, 20, 158)"}} onClick={() => handleColorClick("rgb(171, 20, 158)")}></Button>
            </div>
        
        </div>
    );
}

export default FormationColor;