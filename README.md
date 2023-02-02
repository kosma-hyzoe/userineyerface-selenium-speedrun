# Userinyerface Selenium speedrun

I fist came across [userinyerface.com](https://userinyerface.com/) during my
QA automation engineering internship. I liked the concept a lot, and decided
to "speedrun" it to present what I've learned. It might end up being useful
for loose benchmarking as well.

## How to run

1. Make sure Chrome is installed and 
   [ChromeDriver](https://chromedriver.chromium.org/home) is downloaded.
2. Load Maven dependencies from `pom.xml`
3. Edit the "driverPath" parameter in `config.json`
   (along other parameters as preferred)
4. Run `src/main/java/Main.java`


## How to run tests

1. Load Maven dependencies from `pom.xml`
2. Edit `settings.json` and `testData.json`
   as needed (there are some sane defaults provided, no drivers required)
3. Run one or more tests, either manually or using
   `src/test/resources/testng.xml`

## My strategy

I originally implemented a somewhat class-heavy Page Object Model (POM)
design pattern with an extra library my company uses -
[Aquality](https://github.com/aquality-automation/aquality-selenium-java).

I decided to use "vanilla" Selenium for the actual speedrun, but also
stick to POM. I realise that abstraction and utils (logger, timer etc.)
will slow me down a bit, but this is primarily a demo of my skills and
practices - the speedruning challenge is just a bonus. I might create a
leaner version on another branch and compare results later.

On tests, however, I use Aquality as I originally did. It's quite "bloated",
but useful.

The speedrun currently operates on Chrome, but I'll try to set it up on
Chromium and/or other Chromium-based browsers as well.

## Issues and limitations

* For some reason, the speedrun fails  when running with a 
  `--headless` ChromeOption
* For now the profile image must be uploaded manually.
  `webElement.sendKeys("/path/to/some-image.png")` doesn't work.
  I originally used an AutoIT script on Windows 10, and I'll try to figure
  out something similar for Linux and/or reintroduce the Windows script