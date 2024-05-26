# -*- coding: utf-8 -*-
"""
Created on Fri May 24 13:25:52 2024

@author: CYTech Student
"""

##Génération de la BDD d'étudiant et de leur voeux

import json
import random
import string

# Générer un nom aléatoire
def generate_name():
    return ''.join(random.choices(string.ascii_uppercase, k=5))

# Générer un prénom aléatoire
def generate_firstname():
    return ''.join(random.choices(string.ascii_uppercase, k=5))

# Générer une adresse mail aléatoire
def generate_email():
    return generate_name().lower() + generate_firstname().lower() + "@cy-tech.fr"

# Générer un mot de passe aléatoire
def generate_password():
    return ''.join(random.choices(string.ascii_letters + string.digits, k=8))

# Générer une liste de voeux mélangés
def generate_wishlist():
    wishes = [
        {"nom": "Intelligence Artificielle", "nbPlace": 20, "description": "Ce v�u est top", "filiereEligible": ["GI"], "idVoeux": 101},
        {"nom": "Cyber Securite", "nbPlace": 20, "description": "xdv", "filiereEligible": ["GI"], "idVoeux": 102},
        {"nom": "Informatique embarquee", "nbPlace": 20, "description": "fgj", "filiereEligible": ["GI"], "idVoeux": 103},
        {"nom": "Visual Computing", "nbPlace": 20, "description": "tyk", "filiereEligible": ["GI"], "idVoeux": 104},
        {"nom": "Business Intelligence et Analytics", "nbPlace": 20, "description": "ky", "filiereEligible": ["GI"], "idVoeux": 105}
    ]
    random.shuffle(wishes)
    return wishes

# Générer la base de données
def generate_database(num_students):
    database = []
    for i in range(num_students):
        student = {
            "nom": generate_name(),
            "prenom": generate_firstname(),
            "mail": generate_email(),
            "mdpEtu": generate_password(),
            "numEtu": i,
            "filiere": "GI",
            "moyGen": round(random.uniform(10, 20), 2),
            "listeVoeux": generate_wishlist()
        }
        database.append(student)
    return database

# Générer la base de données avec 100 étudiants
students_database = generate_database(2000)

with open("students_database.json", "w") as outfile:
    json.dump(students_database, outfile, ensure_ascii=True, indent=4)

print("Base de données JSON écrite dans le fichier students_database.json.")
