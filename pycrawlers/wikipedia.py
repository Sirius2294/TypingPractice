from bs4 import BeautifulSoup
import requests


def fill_list(url, n=25):
    intro_list = []
    for x in range(n):
        acceptable = False
        while not acceptable:
            reader = BeautifulSoup(requests.get(url).text, "html.parser")
            intro = reader.find('p').text
            acceptable = is_acceptable(intro)
        intro_list.append(intro)
    return intro_list


def is_usable(letter : str):
    usable_chars = [' ', '`', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '-', '=', 'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', '[', ']', '\\', 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', ';', "'", 'z', 'x', 'c', 'v', 'b', 'n', 'm', ',', '.', '/', '~', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '_', '+', 'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P', '{', '}', '|', 'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', ':', '"', 'Z', 'X', 'C', 'V', 'B', 'N', 'M', '<', '>', '?']
    for c in usable_chars:
        if letter == c:
            return True
            break
    else:
        return False


def is_acceptable(text : str):
    acceptable = True
    for c in text:
        if not is_usable(c):
            acceptable = False
    return acceptable


def cut_article(article : str):
    num_sentence = 0
    for i in range(len(article)):
        if article[i] == '.':
            num_sentence += 1
            if num_sentence == 3:
                article = article[:i+1]
                break
    return article


def clean_articles(article_list):
    for i in range(len(article_list)):
        article_list[i] = cut_article(article_list[i])
    return article_list


def save_to_file(article_list):
    for i in range(len(article_list)):
        file = open("resources/wikipedia/part" + str(i) + ".txt", 'w')
        file.write(article_list[i])
        file.close()


articles = fill_list('https://en.wikipedia.org/wiki/Special:Random', 15)
articles = clean_articles(articles)
save_to_file(articles)
