// 假设医生 ID 存储在一个名为 "doctorId" 的变量中
var doctorId = ...;

// 当表单被提交时，更新医生的个人信息
$('#profile-form').submit(function(event) {
    event.preventDefault();
    $.ajax({
        url: '/api/doctors/' + doctorId,
        type: 'PUT',
        data: {
            name: $('#name').val(),
            department: $('#department').val(),
            introduction: $('#introduction').val()
        },
        success: function() {
            alert('个人信息已更新！');
        }
    });
});

// 从后端获取医生的所有预约请求
$.get('/api/doctors/' + doctorId + '/appointments', function(data) {
    // "data" 是一个包含所有预约请求的数组
    for (var i = 0; i < data.length; i++) {
        var appointment = data[i];
        // 创建一个新的 div 来显示预约请求信息
        var div = $('<div>').text('预约时间: ' + appointment.time);
        // 创建一个 "接受预约" 按钮
        var acceptButton = $('<button>').text('接受预约').click(function() {
            // 点击按钮时接受预约请求
            $.ajax({
                url: '/api/appointments/' + appointment.id + '/accept',
                type: 'PUT',
                success: function() {
                    alert('预约已接受！');
                    div.remove();
                }
            });
        });
