import React, { useState, useEffect, useContext, useRef } from 'react';
import { Table } from 'react-bootstrap';
import { PostContext } from '../../store/post/post-context';
import LoadingSpinner from '../../UI/LoadingSpinner'
import { Link, useNavigate } from 'react-router-dom';
import { Button } from 'react-bootstrap';
import Form from 'react-bootstrap/Form';
import { Validation } from '../../store/validation/validation';


const CreatePost = () => {

  const postCtx = useContext(PostContext);
  const validation = useContext(Validation);
  const PostTitleInputRef = useRef(null);
  const PostContentInputRef = useRef(null);
  const [isLoading, setIsLoading] = useState(false);
  const [titleError, setTitleError] = useState('');
  const navigate = useNavigate();


  const createHandle = async (title, content) => {
    postCtx.createPost(title, content);
  }

  const submitHandler = async (event) => {    
    event.preventDefault();

    const title = PostTitleInputRef.current.value;
    const content = PostContentInputRef.current.value;


    //플레이어 이름 유효성 체크
    const titleCheck = validation.volumnValidator(title, 1, 50);
    setTitleError(titleCheck);

    if(!titleCheck) {
      createHandle(title, content);
    }
  }

  const cancelHandler = () => {
    navigate('/post');
  }




    return(
      <section>
      <h1 class='title'>포스트 생성</h1>
      <div style={{width:"400px"}}>
      <form onSubmit={submitHandler}>
        <div>
          <label htmlFor='postTitle' class='content'>제목 : </label>
          <input class='input-box' type='text' id='postTitle' style={{marginLeft:'15px', width:'150px'}} required ref={PostTitleInputRef}/>
          <div className='error'>{titleError}</div>
        </div>
        <div style={{marginTop : '20px'}}>
        <label htmlFor="postContent" class='content'>내용 : </label>
        <input class='input-box' type='text' id='postContent' style={{marginLeft:'15px', width:'150px'}} required ref={PostContentInputRef}/>
        </div>
        <div style={{marginLeft:'120px', marginTop:'20px', width:'300px'}}>
          <Button type='submit'>추가</Button>
          <Button type='button' onClick={cancelHandler}>취소</Button>
          {isLoading && <LoadingSpinner/>}
        </div>
      </form>
            </div>
    </section>
        
    )
}

export default CreatePost;