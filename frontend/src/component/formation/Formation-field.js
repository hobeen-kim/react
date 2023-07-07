import '../../public/css/formation.css';
import { useState, useRef, useEffect } from 'react';
import FormationPlayer from './FormationPlayer';



const FormationField = ({formation, mainColor, subColor, textColor, subTextColor, uniform}) => {

    const formationXY = {
        '4-4-2': [[190, 200], [320, 200], [80, 350], [190, 350], [320, 350], [430, 350], [80, 570], [190, 570], [320, 570], [430, 570], [245, 720]],
        '4-4-1-1': [[245, 100], [245, 220], [80, 350], [190, 350], [320, 350], [430, 350], [80, 570], [190, 570], [320, 570], [430, 570], [245, 720]],
    }

    const uniformColor = uniform === 'plain' ? mainColor : subColor;

    return (
        
        <div className='formation-formation'>
            {formationXY[formation].map((position, index) => (
                <FormationPlayer 
                    x={position[0]} 
                    y={position[1]} 
                    mainColor={mainColor} 
                    subColor={subColor} 
                    textColor={textColor}
                    subTextColor={subTextColor}
                    uniformColor={uniformColor}
                    id={index + 1} 
                    backNumber={index + 1} 
                    key={`${formation}-${index}`} 
                />
))}
        </div>
    )
}

export default FormationField;
