import React, { useState, useEffect, useContext, useRef } from 'react';
import { Table } from 'react-bootstrap';
import { PostContext } from '../../store/post/post-context';
import LoadingSpinner from '../../UI/LoadingSpinner'
import { Link, useNavigate } from 'react-router-dom';
import { Button } from 'react-bootstrap';



const PostList = () => {

  const [posts, setPosts] = useState(null);


    return(
        <div>
          포스트 리스트 페이지
        </div>
        
    )
}

export default PostList;