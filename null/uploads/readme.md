### FileUploadService

  curl -X POST http://m-miyawaki.net/api/files/upload -H "Content-Type: multipart/form-data" -F "file=@C:/Users/miyaw/Documents/workspace/FileUploadService/readme.md" -F "title=MyFileTitle" -F "userGroup=EngineeringTeam"
  
curl -X POST http://localhost:8080/api/files/upload -H "Content-Type: multipart/form-data" -F "file=@C:/Users/miyaw/Documents/workspace/FileUploadService/readme.md" -F "title=MyFileTitle" -F "userGroup=EngineeringTeam"
  
  netstat -ano | findstr :8080
  
  
  tasklist | findstr 12345
  
  
  taskkill /PID 27116 /F
  