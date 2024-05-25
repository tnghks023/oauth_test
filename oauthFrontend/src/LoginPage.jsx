import React from "react";

const LoginPage = () => {
  document.body.classList.add("gradient-custom");
  return (
    <section className="d-flex vh-100">
      <div className="container-fluid row justify-content-center align-content-center">
        <div className="card bg-dark" style={{ borderRadius: "1rem" }}>
          <div className="card-body p-5 text-center">
            <h2 className="text-white">LOGIN</h2>
            <p className="text-white-50 mt-2 mb-5">
              Please log in to use the service!
            </p>

            <div className="mb-2">
              <a href="http://localhost:8080/oauth/kakao">
                <img src="/src/assets/img/kakao_login.png"></img>
              </a>
            </div>
            <div className="mb-2">
              <a href="http://localhost:8080/oauth/naver">
                <img
                  style={{ width: "183px" }}
                  src="/src/assets/img/naver_login.png"
                ></img>
              </a>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
};

export default LoginPage;
