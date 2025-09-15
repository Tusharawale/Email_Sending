# Email_Sending
# Day 1 Testing Simple Mail Sending



* The **packages** you imported
* The **objects** you created
* The **methods** you used
* The **logic** and flow of the program

---

## 1. **Packages imported**

* `javax.mail.*`  
  Provides core classes for email handling like `Session`, `Message`, `Transport`, `Authenticator`, etc.

* `javax.mail.internet.InternetAddress`  
  Represents email addresses.

* `javax.mail.internet.MimeMessage`  
  Represents an email message object that supports MIME.

* `java.util.Properties`  
  Used to create a property list (key-value pairs), commonly used to configure the mail session.

---

## 2. **Objects created and their purposes**

* **`Properties properties = new Properties();`**  
  Stores configuration settings for the mail session, such as SMTP server info.

* **`Session session = Session.getInstance(properties, new Authenticator() {...});`**  
  Represents a mail session with authentication. It handles the connection to the SMTP server.

* **`MimeMessage m = new MimeMessage(session);`**  
  Represents the actual email message to be sent, constructed using the session.

* **`InternetAddress` (for `from` and `to`)**  
  Encapsulates email addresses.

---

## 3. **Methods used**

### From `Properties` class:

* `put(key, value)`  
  Sets properties like SMTP host, port, SSL, authentication.

### From `Session` class:

* `getInstance(Properties, Authenticator)`  
  Creates a mail session with authentication.

* `setDebug(boolean)`  
  Enables or disables debug logging for the session.

### From `MimeMessage` class:

* `setFrom(Address)`  
  Sets the sender email address.

* `addRecipient(Message.RecipientType, Address)`  
  Adds a recipient (To, CC, BCC) to the message.

* `setSubject(String)`  
  Sets the email subject.

* `setText(String)`  
  Sets the email body (plain text).

### From `Transport` class:

* `send(Message)`  
  Sends the email message.

### From `Authenticator` class:

* `getPasswordAuthentication()`  
  Provides username/password for SMTP authentication.

---

## 4. **Logic / Flow of the program**

1. **Starting point:** `main` method prints a starting message.

2. Define the email parameters:

   * `message` (email body)  
   * `subject`  
   * `to` (recipient email)  
   * `from` (sender email)

3. Call `sendEmail` with these parameters.

4. **Inside `sendEmail`:**

   * Create a `Properties` object and set SMTP configuration:

     * SMTP host: `smtp.gmail.com`  
     * Port: 465 (SSL port)  
     * Enable SSL (`mail.smtp.ssl.enable`)  
     * Enable SMTP authentication

   * Create a mail `Session` with an `Authenticator` that returns the sender email and password.

   * Enable debug logging on the session for detailed SMTP output.

   * Create a `MimeMessage`:

     * Set `From` address  
     * Add recipient(s)  
     * Set the subject  
     * Set the message body

   * Use `Transport.send()` to send the email.

   * Catch any exceptions and print the stack trace.

5. Print success or failure messages accordingly.

---

## 5. **Additional notes**

* Your SMTP authentication uses:

  ```java
  new PasswordAuthentication("tusharawale904904@gmail.com", "Password")
  
  
## 6. **I use this vedio **
`https://www.youtube.com/watch?v=l0J-Edn76js&t=2483s`

## 7. **WE use an Email API**

        <dependencies>
                 <dependency>
                     <groupId>com.sun.mail</groupId>
                     <artifactId>javax.mail</artifactId>
                     <version>1.6.2</version>
                 </dependency>
       </dependencies>
   
#Day 2 Testing Simple Mail with Attachment
