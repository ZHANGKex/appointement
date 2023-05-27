// Call API to get profile information and populate the form
// Call API to get appointments and populate the list

function submitProfile() {
    // Get data from form
    var name = document.getElementById('name').value;
    var age = document.getElementById('age').value;

    // Create object to send to API
    var data = {
        name: name,
        age: age
        // Add other fields as necessary
    };

    // Call API to update profile
    // This will depend on your backend implementation
    // It will look something like this:
    // fetch('/api/patients/{patientId}', {
    //     method: 'PUT',
    //     body: JSON.stringify(data),
    //     headers: {
    //         'Content-Type': 'application/json'
    //     }
    // }).then(function(response) {
    //     // Handle response
    // });
}

function cancelAppointment(appointmentId) {
    // Call API to cancel appointment
    // This will depend on your backend implementation
    // It will look something like this:
    // fetch('/api/appointments/{appointmentId}', {
    //     method: 'DELETE'
    // }).then(function(response) {
    //     // Handle response
    // });
}

// 假设病人 ID 存储在一个名为 "patientId" 的变量中
var patientId = ...;

// 从后端获取病人的所有预约
$.get('/api/patients/' + patientId + '/appointments', function(data) {
    // "data" 是一个包含所有预约的数组
    for (var i = 0; i < data.length; i++) {
        var appointment = data[i];
        // 创建一个新的 div 来显示预约信息
        var div = $('<div>').text('预约时间: ' + appointment.time);
        // 创建一个 "取消预约" 按钮
        var button = $('<button>').text('取消预约').click(function() {
            // 点击按钮时取消预约
            $.ajax({
                url: '/api/appointments/' + appointment.id,
                type: 'DELETE',
                success: function(result) {
                    // 如果成功取消预约，从页面上删除该预约
                    div.remove();
                }
            });
        });
        // 将按钮添加到 div 中
        div.append(button);
        // 将 div 添加到 "appointments" div 中
        $('#appointments').append(div);
    }
});
