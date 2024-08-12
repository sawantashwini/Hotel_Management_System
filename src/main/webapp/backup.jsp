<%@page import="com.service.LogFileService"%>
<%@ page import="java.io.*" %>
<%@ page import="java.sql.*" %>

<% 
String current_date = LogFileService.changeFormate(LogFileService.changeFormateOfCurrentDate("yyyy-MM-dd"), "yyyy-MM-dd", "dd-MM-yyyy");
String database = "hotel_management_db_2023";
String filename = database + "_backup_" + current_date + ".sql";
String filepath = "C:/wamp/bin/mysql/mysql5.1.36/bin/"; 

String url = "jdbc:mysql://localhost:3306/" + database;
String username = "root";
String password = "";

String backupFolderPath = "D:/backup/";
String backupFilePath = backupFolderPath + filename;

try {
    // Create the backup folder if it doesn't exist
    File backupFolder = new File(backupFolderPath);
    if (!backupFolder.exists()) {
        boolean created = backupFolder.mkdirs();
        if (!created) {
            throw new IOException("Failed to create the backup folder.");
        }
    }

    // Establish a connection to the MySQL database
    Class.forName("com.mysql.jdbc.Driver");
    Connection connection = DriverManager.getConnection(url, username, password);
    Statement statement = connection.createStatement();

    // Export the database schema and data to a file
    ProcessBuilder processBuilder = new ProcessBuilder(
        filepath + "mysqldump.exe", 
        "--user=" + username, 
        "--password=" + password, 
        "--databases", database
    );
    processBuilder.redirectOutput(new File(backupFilePath));
    Process process = processBuilder.start();

    // Get the input stream from the process
    InputStream inputStream = process.getInputStream();
    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
    String line;

    // Count the total number of lines in the output
    int totalLines = 0;
    while ((line = reader.readLine()) != null) {
        totalLines++;
    }

    reader.close();
    inputStream.close();

    // Get the input stream again to read the data and write it to the file
    inputStream = process.getInputStream();
    reader = new BufferedReader(new InputStreamReader(inputStream));
    BufferedWriter writer = new BufferedWriter(new FileWriter(backupFilePath));

    // Write the data to the file line by line and calculate the progress percentage
   // ...

int currentLine = 0;
while ((line = reader.readLine()) != null) {
    writer.write(line);
    writer.newLine();
    currentLine++;

    // Calculate the progress percentage
    int progress = (currentLine * 100) / totalLines;

    // Store the progress value in the session attribute
    session.setAttribute("progress_backup", progress);

    // Flush the output to ensure it's sent to the client immediately
    out.flush();
}



// ...



    writer.close();
    reader.close();
    inputStream.close();

    int exitCode = process.waitFor();
    
 // Store the final progress value (100%) in the session attribute
    session.setAttribute("progress_backup", 90);

    if (exitCode == 0) {
        out.println("Database backup stored successfully.");
        
    } else {
        out.println("Database backup failed.");
    }

    // Close the database connection
    statement.close();
    connection.close();
} catch (Exception e) {
    e.printStackTrace();
}
%>
