import React, { useEffect } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import axios from "axios";

const NaverRedirectPage = () => {
  const location = useLocation();
  const navigate = useNavigate();

  const handleOAuthNaver = async (code) => {
    try {
      const response = await axios.get(
        `http://localhost:8080/oauth/login/naver?code=${code}`
      );
      const data = response.data;
      alert("로그인 성공: " + data);
      //navigate("/success");
    } catch (error) {
      alert("로그인 실패: " + error);
      //navigate("/fail");
    }
  };

  useEffect(() => {
    const searchParams = new URLSearchParams(location.search);
    const code = searchParams.get("code");
    if (code) {
      alert("code=" + code);
      handleOAuthNaver(code);
    }
  }, [location]);

  return (
    <div>
      <div>Processing</div>
    </div>
  );
};

export default NaverRedirectPage;
