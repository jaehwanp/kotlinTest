import React, { useState, useEffect } from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import './App.css';
import axios from 'axios';
import Main from './Main';
import Login from './Login';
import Account from './Account';
import About from './About';
import ErrorPage from './ErrorPage';

function Header() {
  return <header>
    <h1><a href="/">QMOV</a></h1>
  </header>
}

function Nav(props) {
  return <ol>
    <li><a href={props.url.accountUrl}>회원가입</a></li>
    <li><a href={props.url.loginUrl}>로그인</a></li>
    <li><a href={props.url.aboutUrl}>about</a></li>
  </ol>
}

function App() {

  const baseUrl = "http://localhost:8080"

  const myUrl = {
    mainUrl : "/",
    loginUrl : "/login",
    accountUrl : "/account",
    aboutUrl : "/about",
  }

  // 경로 설정
  const [id, setId] = useState("")
  const [pw, setPw] = useState("")

  useEffect(() => {
    // 컴포넌트 시작시
    // getAccounts();
  }, [])

  async function getAccounts() {
    await axios  // 응답 기다림
      .get(baseUrl + "/account/1") 
      .then((response) => {
        //console.log(response.data)
      })
      .catch((error) => {
        console.error(error)
      })
  }

  function createAccount(e) {
    e.preventDefault();

    const makeSign =  async () => {
      await axios
        .post(baseUrl + myUrl.accountUrl, {
          name: id,
          password: pw
        })
        .then((response) => {
          setId("");
          setPw("");
          getAccounts();
        })
        .catch((error) => {
          console.error(error);
        })
    }
    makeSign();
    alert('회원가입 성공')
  }

  function handleChange(e) {
    e.preventDefault();

    if(e.target.name === "name") {
      setId(e.target.value)
    } else if(e.target.name === "password") {
      setPw(e.target.value)
    }
  }

  return (
    <div className="App">
      <Header/>
      <Nav url={myUrl}/>
      <BrowserRouter>
        <Routes>
          <Route path="*" element={<ErrorPage/>}></Route>
          <Route path={myUrl.mainUrl} element={<Main/>}></Route>
          <Route path={myUrl.loginUrl} element={<Login/>}></Route>
          <Route path={myUrl.aboutUrl} element={<About/>}></Route>
          <Route path={myUrl.accountUrl} element={<Account handleSubmit={createAccount}
              id={id}
              pw={pw}
              handleChange={handleChange}
            />}></Route>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
