<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<title>${applicationScope.title}</title>
<%@ include file="common/static-content.jsp" %>
</head>

<body>

<%@ include file="common/header.jsp" %>

	<!-- banner -->
	<div class="banner">
		<div class="w3l_banner_nav_left">
			<nav class="navbar nav_bottom">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header nav_2">
					<button type="button"
						class="navbar-toggle collapsed navbar-toggle1"
						data-toggle="collapse" data-target="#bs-megadropdown-tabs">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
				</div>
				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse" id="bs-megadropdown-tabs">
					<ul class="nav navbar-nav nav_1">
						<li><a href="products.html">Branded Foods</a></li>
						<li><a href="household.html">Households</a></li>
						<li class="dropdown mega-dropdown active"><a href="#"
							class="dropdown-toggle" data-toggle="dropdown">Veggies &
								Fruits<span class="caret"></span>
						</a>
							<div
								class="dropdown-menu mega-dropdown-menu w3ls_vegetables_menu">
								<div class="w3ls_vegetables">
									<ul>
										<li><a href="vegetables.html">Vegetables</a></li>
										<li><a href="vegetables.html">Fruits</a></li>
									</ul>
								</div>
							</div></li>
						<li><a href="kitchen.html">Kitchen</a></li>
						<li><a href="short-codes.html">Short Codes</a></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">Beverages<span class="caret"></span></a>
							<div
								class="dropdown-menu mega-dropdown-menu w3ls_vegetables_menu">
								<div class="w3ls_vegetables">
									<ul>
										<li><a href="drinks.html">Soft Drinks</a></li>
										<li><a href="drinks.html">Juices</a></li>
									</ul>
								</div>
							</div></li>
						<li><a href="pet.html">Pet Food</a></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">Frozen Foods<span class="caret"></span></a>
							<div
								class="dropdown-menu mega-dropdown-menu w3ls_vegetables_menu">
								<div class="w3ls_vegetables">
									<ul>
										<li><a href="frozen.html">Frozen Snacks</a></li>
										<li><a href="frozen.html">Frozen Nonveg</a></li>
									</ul>
								</div>
							</div></li>
						<li><a href="bread.html">Bread & Bakery</a></li>
					</ul>
				</div>
				<!-- /.navbar-collapse -->
			</nav>
		</div>
		<div class="w3l_banner_nav_right">
			<!-- login -->
			<div class="w3_login">
				<h3>Verfiy Customer</h3>
				<div class="w3_login_module">
					<div class="module form-module">
						<div class="toggle">
							<i class="fa fa-times fa-pencil"></i>
							<div class="tooltip">Click Me</div>
						</div>
						<div class="form">
							<h2>Login to your account</h2>
							<i>${requestScope.msg}</i>
							<form action="login" method="post">
								<input type="text" name="username" placeholder="Enter Username"
									required=" "> <input type="text" name="otp"
									placeholder="Enter OTP" required=" "> 
									<input type="submit" value="Verify">
							</form>
						</div>
						</div>
						<div class="cta">
							<a href="#">Forgot your password?</a>
						</div>
					</div>
				</div>
				<script>
					$('.toggle').click(function() {
						// Switches the Icon
						$(this).children('i').toggleClass('fa-pencil');
						// Switches the forms  
						$('.form').animate({
							height : "toggle",
							'padding-top' : 'toggle',
							'padding-bottom' : 'toggle',
							opacity : "toggle"
						}, "slow");
					});
				</script>
			</div>
			<!-- //login -->
		</div>
		<div class="clearfix"></div>
	</div>
	<!-- //banner -->
	
	<%@ include file="common/footer.jsp" %>
</body>
</html>