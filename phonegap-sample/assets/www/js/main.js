$(document).ready(function(){
	document.addEventListener("deviceready", function() {
		var url;
		$("#camera").click(function() {
			navigator.camera.getPicture(function(file) {
				url = file;
			}, function(error) {
				alert("camera error:" + error);
			}, {
				quality : 50,
				destinationType : Camera.DestinationType.FILE_URI
			});
		});
		$("#upload").click(function() {
			if (url) {
				alert("ファイルアップロード:" + url);
	            var options = new FileUploadOptions();
	            options.fileKey="fileInput";
	            options.fileName=url.substr(url.lastIndexOf('/')+1) + ".jpg";
	            options.mimeType="image/jpeg";
	
	            var params = new Object();
	            options.params = params;
	
	            var ft = new FileTransfer();
	            ft.upload(url, "http://192.168.0.160:8080/wicket/uploader?wicket:interface=:0:simpleUpload::IFormSubmitListener::", win, fail, options);
			} else {
				alert("カメラで撮ってください");
			}
		});
        function win(r) {
            console.log("Code = " + r.responseCode);
            console.log("Response = " + r.response);
            console.log("Sent = " + r.bytesSent);
            alert("Upload done.");
        }

        function fail(error) {
            alert("An error has occurred: Code = " = error.code);
        }
	});
});