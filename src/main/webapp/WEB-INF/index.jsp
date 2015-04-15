<%--
  Created by IntelliJ IDEA.
  User: Vadym Akymov
  Date: 15.04.15
  Time: 1:37
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Home</title>
    <meta charset="utf-8">
    <meta name = "format-detection" content = "telephone=no" />
    <link rel="shortcut icon" href="resources/images/favicon.ico" />
    <link rel="stylesheet" href="resources/booking/css/booking.css">
    <link rel="stylesheet" href="resources/css/camera.css">
    <link rel="stylesheet" href="resources/css/owl.carousel.css">
    <link rel="stylesheet" href="resources/css/style.css">
    <%--<script src="/resources/js/jquery.js"></script>--%>
    <script src="resources/js/jquery-migrate-1.2.1.js"></script>
    <script src="resources/js/script.js"></script>
    <script src="resources/js/superfish.js"></script>
    <%--<script src="resources/js/jquery.ui.totop.js"></script>--%>
    <%--<script src="resources/js/jquery-ui-1.10.3.custom.min.js"></script>--%>
    <%--<script src="resources/js/jquery.fancyform.js"></script>--%>
    <%--<script src="resources/js/jquery.placeholder.js"></script>--%>
    <%--<script src="resources/js/regula.js"></script>--%>

    <script src="resources/js/jquery.equalheights.js"></script>
    <script src="resources/js/jquery.mobilemenu.js"></script>
    <script src="resources/js/jquery.easing.1.3.js"></script>
    <script src="resources/js/owl.carousel.js"></script>
    <script src="resources/js/camera.js"></script>
    <!--[if (gt IE 9)|!(IE)]><!-->
    <script src="resources/js/jquery.mobile.customized.min.js"></script>
    <!--<![endif]-->
    <script src="resources/booking/js/booking.js"></script>
    <script>
        $(document).ready(function(){
            jQuery('#camera_wrap').camera({
                loader: false,
                pagination: false ,
                minHeight: '444',
                thumbnails: false,
                height: '28.28125%',
                caption: true,
                navigation: true,
                fx: 'mosaic'
            });
            $().UItoTop({ easingType: 'easeOutQuart' });
        });
    </script>
    <!--[if lt IE 8]>
    <div style=' clear: both; text-align:center; position: relative;'>
        <a href="http://windows.microsoft.com/en-US/internet-explorer/products/ie/home?ocid=ie6_countdown_bannercode">
            <img src="http://storage.ie6countdown.com/assets/100/images/banners/warning_bar_0000_us.jpg" border="0" height="42" width="820" alt="You are using an outdated browser. For a faster, safer browsing experience, upgrade for free today." />
        </a>
    </div>
    <![endif]-->
    <!--[if lt IE 9]>
    <script src="resources/js/html5shiv.js"></script>
    <link rel="stylesheet" media="screen" href="resources/css/ie.css">
    <![endif]-->
</head>
<body class="page1" id="top">
<div class="main">
    <!--==============================header=================================-->
    <header>
        <div class="menu_block ">
            <div class="container_12">
                <div class="grid_12">
                    <nav class="horizontal-nav full-width horizontalNav-notprocessed">
                        <ul class="sf-menu">
                            <li class="current"><a href="index.html">Home</a></li>
                            <li><a href="index-1.html">About</a></li>
                            <li><a href="index-2.html">Cars</a></li>
                            <li><a href="index-3.html">Services</a></li>
                            <li><a href="index-4.html">Contacts</a></li>
                        </ul>
                    </nav>
                    <div class="clear"></div>
                </div>
                <div class="clear"></div>
            </div>
        </div>
        <div class="container_12">
            <div class="grid_12">
                <h1>
                    <a href="index.html">
                        <img src="resources/images/logo.png" alt="Your Happy Family">
                    </a>
                </h1>
            </div>
        </div>
        <div class="clear"></div>
    </header>
    <div class="slider_wrapper ">
        <div id="camera_wrap" class="">
            <div data-src="resources/images/slide.jpg" ></div>
            <div data-src="resources/images/slide1.jpg" ></div>
            <div data-src="resources/images/slide2.jpg"></div>
        </div>
    </div>
    <div class="container_12">
        <div class="grid_4">
            <div class="banner">
                <div class="maxheight">
                    <div class="banner_title">
                        <img src="resources/images/icon1.png" alt="">
                        <div class="extra_wrapper">Fast&amp;
                            <div class="color1">Safe</div>
                        </div>
                    </div>
                    Dorem ipsum dolor sit amet, consectetur adipiscinger elit. In mollis erat mattis neque facilisis, sit ameter ultricies erat rutrum. Cras facilisis, nulla vel viver auctor, leo magna sodales felis, quis malesuad
                    <a href="#" class="fa fa-share-square"></a>
                </div>
            </div>
        </div>
        <div class="grid_4">
            <div class="banner">
                <div class="maxheight">
                    <div class="banner_title">
                        <img src="resources/images/icon2.png" alt="">
                        <div class="extra_wrapper">Best
                            <div class="color1">Prices</div>
                        </div>
                    </div>
                    Hem ipsum dolor sit amet, consectetur adipiscinger elit. In mollis erat mattis neque facilisis, sit ameter ultricies erat rutrum. Cras facilisis, nulla vel viver auctor, leo magna sodales felis, quis malesuader
                    <a href="#" class="fa fa-share-square"></a>
                </div>
            </div>
        </div>
        <div class="grid_4">
            <div class="banner">
                <div class="maxheight">
                    <div class="banner_title">
                        <img src="resources/images/icon3.png" alt="">
                        <div class="extra_wrapper">Package
                            <div class="color1">Delivery</div>
                        </div>
                    </div>
                    Kurem ipsum dolor sit amet, consectetur adipiscinger elit. In mollis erat mattis neque facilisis, sit ameter ultricies erat rutrum. Cras facilisis, nulla vel viver auctor, leo magna sodales felis, quis malesuki
                    <a href="#" class="fa fa-share-square"></a>
                </div>
            </div>
        </div>
        <div class="clear"></div>
    </div>
    <div class="c_phone">
        <div class="container_12">
            <div class="grid_12">
                <div class="fa fa-phone"></div>+ 1800 559 6580
                <span>ORDER NOW!</span>
            </div>
            <div class="clear"></div>
        </div>
    </div>
    <!--==============================Content=================================-->
    <div class="content"><div class="ic">More Website Templates @ TemplateMonster.com - April 07, 2014!</div>
        <div class="container_12">
            <div class="grid_5">
                <h3>Booking Form</h3>
                <form id="bookingForm">
                    <div class="fl1">
                        <div class="controlHolder"><div class="tmInput">
                            <input name="Name" placeholder="Name:" type="text" data-constraints="@NotEmpty @Required @AlphaSpecial" id="regula-generated-312345">
                        </div></div>
                        <div class="controlHolder"><div class="tmInput">
                            <input name="From" placeholder="From:" type="text" data-constraints="@NotEmpty @Required " id="regula-generated-537913">
                        </div></div>
                    </div>
                    <div class="fl1">
                        <div class="controlHolder"><div class="tmInput">
                            <input name="Email" placeholder="Email:" type="text" data-constraints="@NotEmpty @Required @Email" id="regula-generated-473998">
                        </div></div>
                        <div class="controlHolder"><div class="tmInput mr0">
                            <input name="To" placeholder="To:" type="text" data-constraints="@NotEmpty @Required" id="regula-generated-75264">
                        </div></div>
                    </div>
                    <div class="clear"></div>
                    <strong>Time</strong>
                    <div class="controlHolder"><div class="tmInput">
                        <input name="Time" placeholder="" type="text" data-constraints="@NotEmpty @Required" id="regula-generated-2354">
                    </div></div>
                    <div class="clear"></div>
                    <strong>Date</strong>
                    <div class="controlHolder"><label class="tmDatepicker">
                        <input type="text" name="Date" placeholder="20/05/2014" data-constraints="@NotEmpty @Required @Date" id="dp1429112311767" class="hasDatepicker">
                    </label></div>
                    <div class="clear"></div>
                    <div class="controlHolder"><div class="tmRadio">
                        <p>Comfort</p>
                        <input name="Comfort" type="radio" id="tmRadio0" data-constraints="@RadioGroupChecked(name=&quot;Comfort&quot;, groups=[RadioGroup])" checked="" style="display: none;"><strong class="checked"></strong>
                        <span>Cheap</span>
                        <input name="Comfort" type="radio" id="tmRadio1" data-constraints="@RadioGroupChecked(name=&quot;Comfort&quot;, groups=[RadioGroup])" style="display: none;"><strong class="unchecked"></strong>
                        <span>Standart</span>
                        <input name="Comfort" type="radio" id="tmRadio2" data-constraints="@RadioGroupChecked(name=&quot;Comfort&quot;, groups=[RadioGroup])" style="display: none;"><strong class="unchecked"></strong>
                        <span>Lux</span>
                    </div></div>
                    <div class="clear"></div>
                    <div class="fl1 fl2">
                        <em>Adults</em>
                        <div class="controlHolder"><select name="Adults" class="tmSelect auto" data-class="tmSelect tmSelect2" data-constraints="" style="display: none;">
                            <option>1</option>
                            <option>1</option>
                            <option>2</option>
                            <option>3</option>
                        </select><ul class="tmSelect tmSelect2 auto trans-element"><li><span>1</span><ul style="display: none;" class="transformSelectDropdown"><li data-settings="" class=""><span>1</span></li><li data-settings="" class=""><span>2</span></li><li data-settings="" class=""><span>3</span></li></ul></li></ul></div>
                        <div class="clear height1"></div>
                    </div>
                    <div class="fl1 fl2">
                        <em>Children</em>
                        <div class="controlHolder"><select name="Children" class="tmSelect auto" data-class="tmSelect tmSelect2" data-constraints="" style="display: none;">
                            <option>0</option>
                            <option>0</option>
                            <option>1</option>
                            <option>2</option>
                        </select><ul class="tmSelect tmSelect2 auto trans-element"><li><span>0</span><ul style="display: none;" class="transformSelectDropdown"><li data-settings="" class=""><span>0</span></li><li data-settings="" class=""><span>1</span></li><li data-settings="" class=""><span>2</span></li></ul></li></ul></div>
                    </div>
                    <div class="clear"></div>
                    <div class="controlHolder"><div class="tmTextarea">
                        <textarea name="Message" placeholder="Message" data-constraints="@NotEmpty @Required @Length(min=20,max=999999)" id="regula-generated-388570"></textarea>
                    </div></div>
                    <a href="#" class="btn" data-type="submit">Submit</a>
                </form>
            </div>
            <div class="grid_6 prefix_1">
                <a href="index2.html" class="type"><img src="resources/images/page1_img1.jpg" alt=""><span class="type_caption">Economy</span></a>
                <a href="index2.html" class="type"><img src="resources/images/page1_img2.jpg" alt=""><span class="type_caption">Standard</span></a>
                <a href="index2.html" class="type"><img src="resources/images/page1_img3.jpg" alt=""><span class="type_caption">Lux</span></a>
            </div>
            <div class="clear"></div>
        </div>
    </div>
</div>
<!--==============================footer=================================-->
<footer>
    <div class="container_12">
        <div class="grid_12">
            <div class="f_phone"><span>Call Us:</span> + 1800 559 6580</div>
            <div class="socials">
                <a href="#" class="fa fa-twitter"></a>
                <a href="#" class="fa fa-facebook"></a>
                <a href="#" class="fa fa-google-plus"></a>
            </div>
            <div class="copy">
                <div class="st1">
                    <div class="brand">Track<span class="color1">S</span>ee </div>
                    &copy; 2014	| <a href="#">Privacy Policy</a> </div> Website designed by <a href="https://github.com/TrackSee" rel="nofollow">TrackSee team</a>
            </div>
        </div>
        <div class="clear"></div>
    </div>
</footer>
<script>
    $(function (){
        $('#bookingForm').bookingForm({
            ownerEmail: '#'
        });
    })
    $(function() {
        $('#bookingForm input, #bookingForm textarea').placeholder();
    });
</script>
</body>
</html>