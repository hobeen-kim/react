import '../../public/css/logginedPage.css'

import {Swiper, SwiperSlide} from 'swiper/react';
import SwiperCore, {Navigation,Pagination,Autoplay} from 'swiper';
import { useNavigate } from 'react-router-dom';


import 'swiper/css';
import 'swiper/css/autoplay'
import 'swiper/scss/navigation'
import 'swiper/scss/pagination'
import '../../public/css/home.css'

import logo from "../../public/image/logo.png";
import logoText from "../../public/image/logo-text.png";
import Banner1Image from "../../public/image/banner1-innerimage.jpg";
import Banner2Image from "../../public/image/banner2-innerimage.jpg";


SwiperCore.use([Navigation, Pagination, Autoplay]) // Swiper

const Logined = () => {

  const navigate = useNavigate();

  
  
      return (
            <>
            <div className='page-container' style={{fontSize:'32pxs'}}>
              <div>
                <Swiper
                  className = "home-banner"
                  effect={"coverflow"}
                  grabCursor={true}
                  spaceBetween={50}
                  slidesPerView={1}
                  navigation
                  pagination={{clickable : true}}
                  autoplay={{
                        delay: 2500,
                        disableOnInteraction: false,
                      }}
                  speed={1000}
                  loop={true}>
                    <SwiperSlide onClick={() => navigate('/hitmap')}>
                      <div className='banner1'>
                        <div className='banner-text'>
                          <p className='banner-title'>히트맵</p>
                          <h3>히트맵을 작성하고 경기를 분석하세요!</h3>
                          보다 전문적으로 경기력을 분석하고 활용할 수 있습니다.
                        </div>
                        <img className='banner-image1' src={Banner1Image} alt="banner1" />
                      </div>
                      
                    </SwiperSlide>
                    <SwiperSlide onClick={() => navigate('/formation')}>
                    <div className='banner2'>
                    <div className='banner-text'>
                    <p className='banner-title'>포메이션</p>
                        <h2>포메이션을 작성하고 경기를 분석하세요!</h2>
                        보다 전문적으로 경기력을 분석하고 활용할 수 있습니다.
                      </div>
                      <div className='banner-circle'>
                      </div>
                      </div>
                    </SwiperSlide>
                </Swiper>
              </div>
            </div>
            <div className='home-content'>
                  <img src={logo} className="home-logo-img" alt="logo" />
                  <img src={logoText} className="home-logo-text" alt="logoText" />
                  <br/>
                  <h3>전문적인 경기 분석을 위한 <br/>다양한 도구를 제공합니다.</h3>
            </div>
            </>
          )
      }

export default Logined;