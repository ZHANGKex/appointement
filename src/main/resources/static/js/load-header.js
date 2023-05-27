document.addEventListener('DOMContentLoaded', function () {
    loadHeader();
});

function loadHeader() {
    const headerPlaceholder = document.getElementById('header-placeholder');
    const xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            headerPlaceholder.innerHTML = this.responseText;
        }
    };
    xhr.open('GET', 'header.html', true);
    xhr.send();
}

// 示例数据，实际使用时需要用后端语言和数据库动态生成
const hospitals = [
    {
      id: 1,
      name: "医院1",
      description: "医院1，成立于XXXX年，是一所综合医院，设有内科、外科、妇产科、儿科、五官科等30多个专科。地址:XXXXXXXX",
      imgUrl: "医院1.jpg",
      mapUrl: "http://j.map.baidu.com/XXXXXXXX",
    },
    {
      id: 2,
      name: "医院2",
      description: "医院2，成立于XXXX年，是一所传染病医院，主要负责各种传染病的防治。地址:XXXXXXXX",
      imgUrl: "医院2.jpg",
      mapUrl: "http://j.map.baidu.com/XXXXXXXX",
    },
  ];
  
  const doctors = [
    {
      id: 1,
      name: "医师1",
      title: "主任医师",
      expertise: "擅长XXXX",
      experience: "XXX年工作经验",
      imgUrl: "医师1.jpg",
    },
    {
      id: 2,
      name: "医师2",
      title: "副主任医师",
      expertise: "擅长XXXX",
      experience: "XX年工作经验",
      imgUrl: "医师2.jpg",
    },
  ];
  
  const places = [
    { id: 1, name: "101办公室" },
    { id: 2, name: "102办公室" },
  ];
  
  const times = [
    { id: 1, name: "09:00-09:05" },
    { id: 2, name: "09:05-09:10" },
  ];
  
  // 动态生成医院列表
  function generateHospitalList() {
    const hospitalList = document.getElementById("hospital-list");
    hospitals.forEach((hospital) => {
      const hospitalDiv = document.createElement("div");
      hospitalDiv.innerHTML = `
        <img src="${hospital.imgUrl}" width="200px"/>
        <h3>${hospital.name}</h3>
        <p>${hospital.description}</p>
        <a href="${hospital.mapUrl}" target="_blank">查看地图</a>
        <button onclick="window.location.href='appointment.html'">选择${hospital.name}</button>
      `;
      hospitalList.appendChild(hospitalDiv);
    });
  }
  
  // 动态生成医生列表
  function generateDoctorList() {
    const doctorList = document.getElementById("doctor-list");
    doctors.forEach((doctor) => {
      const doctorDiv = document.createElement("div");
      doctorDiv.innerHTML = `
        <img src="${doctor.imgUrl}" width="100px"/>
        <p>${doctor.name}，${doctor.title}，${doctor.expertise}，${doctor.experience}。</p>
      `;
      doctorList.appendChild(doctorDiv);
    });
  }
  
  // 动态生成预约地点列表
  function generatePlaceList() {
    const placeList = document.getElementById("place-list");
    places.forEach((place) => {
      const placeP
  
  