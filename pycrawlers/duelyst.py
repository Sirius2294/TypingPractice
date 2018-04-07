from bs4 import BeautifulSoup
import requests


def fill_list(url):
    reader = BeautifulSoup(requests.get(url).text, "html.parser")
    is_scanning = False
    text = []

    for tag in reader.findAll('p'):
        if str(tag.string) == '0 AE\n':
            is_scanning = True
        if is_scanning and str(tag.string)[len(str(tag.string))-3:] != 'AE\n' and str(tag.string)[len(str(tag.string))-4:] != "AE \n" and str(tag.string) != 'None':
            text.append(str(tag.string))
    return text


def get_sentences(text="", num_of_sentences=2):
    num_of_periods = 0
    for i in range(len(text)):
        if text[i] == '.':
            num_of_periods += 1
            if num_of_periods == num_of_sentences:
                sentences = text[:i+1].strip()
                break
    return sentences


def remove_sentences(text="", num_of_sentences=2):
    num_of_periods = 0
    for i in range(len(text)):
        if text[i] == '.':
            num_of_periods += 1
            if num_of_periods == num_of_sentences:
                text = text[i+1:].strip()
                break
    return text


def separate_sentences(text : str, num_of_sentences : int):
    sentences = []
    while(len(text) != 0):
        sentences.append(get_sentences(text, num_of_sentences).strip())
        text = remove_sentences(text, num_of_sentences).strip()
    return sentences


def compile_into_string(paragraphs=[]):
    text = ""
    for p in paragraphs:
        if not str(p).endswith(" "):
            text += " "
        text += str(p)
    text = text.replace("\n", "")
    text = text.replace("’", "'")
    text = text.replace("−", "-")
    text = text.replace("—", "-")
    return text.strip()


def clean_string(text : str):
    i = 0
    while(i < len(text)):
        if text[i] == ',' and text[i+1] != ' ':
            text = text[:i+1] + ' ' + text[i+1:]
        i += 1
    return text


def save_to_files(list):
    for i in range(len(list)):
        file = open('resources/duelyst/part' + str(i) + '.txt', 'w')
        file.write(str(list[i]))
        file.close()


text_list = fill_list('https://duelyst.gamepedia.com/Game_Lore')
full_text = compile_into_string(text_list)
full_text = clean_string(full_text)
part_list = separate_sentences(full_text, 2)
save_to_files(part_list)
