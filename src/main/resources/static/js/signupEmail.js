//이메일 인증 코드 api 호출
const requestVerificationButton = document.getElementById('requestVerification-btn');

if (requestVerificationButton) {
  console.log('버튼이 올바르게 선택됨');  // DOM 요소가 선택되었는지 확인

  requestVerificationButton.addEventListener('click', function(event) {
    console.log('버튼 클릭됨');  // 클릭 이벤트가 발생하는지 확인
    const email = document.getElementById('inputEmail').value;

    if (!email) {
      alert('이메일을 입력하세요.');
      return;
    }

    fetch('/auth/verify-email', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ email: email })
    })
    .then(response => {
      if (!response.ok) {
        throw new Error('인증코드 전송 실패');
      }
      return response.json();
    })
    .then(data => {
      alert('인증코드가 전송되었습니다.');
      location.replace(`/auth/signup/emailForm?email=${encodeURIComponent(email)}`);
    })
    .catch(error => {
      console.error('Error:', error);
      alert('인증코드 전송 중 오류가 발생했습니다.');
    });
  });
} else {
  console.error('버튼을 찾을 수 없습니다');
}

//이메일 인증 확인 api 호출
const checkVerificationButton = document.getElementById('checkVerification-btn');

if (checkVerificationButton) {
  console.log('확인 버튼이 올바르게 선택됨');  // DOM 요소가 선택되었는지 확인

  checkVerificationButton.addEventListener('click', function(event) {
    console.log('버튼 클릭됨');  // 클릭 이벤트가 발생하는지 확인
    const verificationCode = document.getElementById('inputVerification').value;
    const email = document.getElementById('inputEmail').value;

    if (!verificationCode) {
      alert('인증 코드를 입력하세요.');
      return;
    }

    fetch('/auth/verify-email-check', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({email: email, verificationCode: verificationCode})

    })
    .then(response => {
      if (!response.ok) {
        throw new Error('인증코드 확인 실패');
      }
      return response.json();
    })
    .then(data => {
      alert('인증코드가 확인되었습니다.');
      location.replace(`/auth/signup/emailForm?email=${encodeURIComponent(email)}&verificationCode=${encodeURIComponent(verificationCode)}`);
    })
    .catch(error => {
      console.error('Error:', error);
      alert('인증코드 확인 중 오류가 발생했습니다.');
    });
  });
} else {
  console.error('버튼을 찾을 수 없습니다');
}

//이메일 회원가입 기능
const signupButton = document.getElementById('signup-btn');

if (signupButton) {
  console.log('회원가입 버튼이 올바르게 선택됨');  // DOM 요소가 선택되었는지 확인

  signupButton.addEventListener('click', function(event) {

    console.log('버튼 클릭됨');  // 클릭 이벤트가 발생하는지 확인
    const email = document.getElementById('inputEmail').value;
    const password = document.getElementById('inputPassword').value;
    const nick = document.getElementById('inputNick').value;

    fetch('/auth/signup/email', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({email: email, password: password, nick: nick})

    })
    .then(response => {
      if (!response.ok) {
        throw new Error('회원가입 실패');
      }
      return response.json();
    })
    .then(data => {
      alert('회원가입이 완료되었습니다.');
      location.replace(`/auth/signup/home`);
    })
    .catch(error => {
      console.error('Error:', error);
      alert('회원가입 진행 중 오류가 발생했습니다.');
    });
  });
} else {
  console.error('버튼을 찾을 수 없습니다');
}


