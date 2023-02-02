# Userinyerface Selenium speedrun

![You are awesome](you-are-awesome.png)

I fist came across [userinyerface.com](https://userinyerface.com/) during my
QA automation engineering internship. It challenges the user 
to complete registering on a fictional page which uses the worst possible
(sic!) UI practices. I liked the concept a lot, and decided to "speedrun" it to
present what I've learned. It might end up being useful for loose benchmarking as well.

<!-- TOC -->
  * [How to run](#how-to-run)
  * [How to run tests](#how-to-run-tests)
  * [Current record](#current-record)
  * [Notes on implementation details](#notes-on-implementation-details)
  * [Issues and limitations](#issues-and-limitations)
  * [Specifics on my attempt strategy](#specifics-on-my-attempt-strategy)
<!-- TOC -->

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

## Current record

**Time on page**: 00:00:04

**Measured time**: 0m:4s:647568989ns

## Notes on implementation details

I originally made my tests with a somewhat class-heavy Page Object Model (POM)
design pattern with an extra library my company uses -
[Aquality](https://github.com/aquality-automation/aquality-selenium-java).

I decided to use "vanilla" Selenium for the actual speedrun, but also
stick to POM. I realise that abstraction will slow me down a bit,
but this is primarily a demo of my skills and practices - the speedruning
challenge is just a bonus. I might create a leaner version on another branch
and compare results later.

On tests, however, I use Aquality as I originally did. It's quite "bloated",
but useful. I am NOT going to implement more tests unless I have a very good
reason.

## Issues and limitations

* For some reason, the speedrun fails  when running with a
  `--headless` ChromeOption
* For now the profile image must be uploaded manually.
  `webElement.sendKeys("/path/to/some-image.png")` doesn't work.
  I originally used an AutoIT script on Windows 10. I consider
  doing something similar for Linux and/or reintroducing the Windows script,
  but I'm afraid it's going to be slower (sic!) a manual upload.

## Specifics on my attempt strategy

* I upload an empty file "image" (you can choose any filetype)
  with arrow-right and-then enter keys (via Nautilus on POP!_OS)
* On `Card3PersonalDetails`, I call `.clear()` and `.sendKeys("foo")`
  on all input fields caught found with `driverFindElements()`. 
* Card 3 dropdown fields are more tricky:
  * Non-problematic dropdown field items (i.e. conuntry flags) are selected
  with relative locators
  * "Mrs" title is chosen to avoid changing gender
  * Year, month and day are checked with `if-else` statements
  to make it possible to birthday match age of 0.
  Ideally I would only do it with year, but day and month seem to
  misbehave otherwise.
  * A `for-each` loop with `if-else` is probably far from ideal -
  I might try to improve it later on.
