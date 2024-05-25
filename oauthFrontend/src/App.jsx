import { useState } from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";
import LoginPage from "./LoginPage";
import KakaoRedirectPage from "./assets/KakaoRedirectPage";
import NaverRedirectPage from "./assets/NaverRedirectPage";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<LoginPage />} />
          <Route
            path="/oauth/redirected/kakao"
            element={<KakaoRedirectPage />}
          />
          <Route
            path="/oauth/redirected/naver"
            element={<NaverRedirectPage />}
          />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
