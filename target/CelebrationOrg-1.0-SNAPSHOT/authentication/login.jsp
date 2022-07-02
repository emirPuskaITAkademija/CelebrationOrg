<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Login</title>
</head>
<body>

<div style="justify-content: center; margin-top: 100px; padding-left: 25%; padding-right: 35%;">
    <div class="card shadow-2-strong card-registration" style="border-radius: 15px;">
        <div class="card-body p-4 p-md-5">
            <h3 class="mb-4 pb-2 pb-md-0 mb-md-5">Login Form</h3>
            <form action="authentication" method="post">
                <div class="form-outline mb-4">
                    <label for="username" class="form-label">Username</label>
                    <input id="username" name="username" type="text" placeholder="Enter username.." required
                           class="form-control"/>
                </div>
                <div class="form-outline mb-4">
                    <label for="password" class="form-label">Password</label>
                    <input id="password" name="password" type="password" placeholder="Enter password.." required
                           class="form-control"/>
                </div>
                <!-- Submit button -->
                <button type="submit" class="btn btn-primary btn-block mb-4 w-100">Sign in</button>
                <!-- Register buttons -->
                <div class="text-center">
                    <p>Not a member? <a href="registration">Register</a></p>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>