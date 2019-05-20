import csv

#Opciones del menu principal
MENUPRINCIPAL = {'b': buscar, 'c': contador}
CONTADOR = {'vau': verAusentes, 'vag': verAgregados, 'a': agregarLista, 'r': quitarLista, 'b': borrarLista}

# class Viajero:
#     def __init__(self, uniqueId, name, lastName, phone, email, day, month, year, school, insuranceCode, bloodType, passports, passportNumbers, destination, fatherName, fatherPhone, motherName, motherPhone):
#         self.name = name
#         self.uniqueId = uniqueId
#         self.lastName = lastName
#         self.phone = phone
#         self.email = email
#         self.day = day
#         self.month = month
#         self.year = year
#         self.school = school
#         self.insuranceCode = insuranceCode
#         self.bloodType = bloodType
#         self.passports = passports
#         self.passportNumbers = passportNumbers
#         self.destination = destination
#         self.fatherName = fatherName
#         self.fatherPhone = fatherPhone
#         self.motherName = motherName
#         self.motherPhone = motherPhone


DATABASE = {}
FIELDS = ["uniqueId", "Nombre", "Apellidos", "Telefono", "Correo", "Dia", "Mes", "Year", "Colegio", "Codio de Seguro", "Tipo de Sangre", "Pasaportes", "Numero de Pasaportes", "Destino", "Nombre Padre", "Telefono Padre", "Nombre Madre", "Telefono Madre"]

# Abre la base de datos
with open('fakedatabase.csv') as csv_file:
    csv_reader = csv.reader(csv_file, delimiter=',')
    next(csv_reader)
    for line in csv_reader:
        DATABASE[str(line[0])] = {'id': line[0], 'name': line[1], 'lastName': line[2], 'phone': line[3], 'email': line[4], 'day': line[5], 'month': line[6], 'year': line[7], 'school': line[8], 'insuranceCode': line[9], 'bloodType': line[10], 'passports': line[11], 'passportNumbers': line[12], 'destination': line[13], 'fatherName': line[14], 'fatherPhone': line[15], 'motherName': line[16], 'motherPhone': line[17]}
        # line[0] = Viajero(line[0], line[1], line[2], line[3], line[4], line[5], line[6], line[7], line[8], line[9], line[10], line[11], line[12], line[13], line[14], line[15], line[16], line[17])

#Busca a una persona en la base de datos y regresa su info
def buscar():
    persona = input('A quien quieres busar? ')
    count = 0
    for info in DATABASE[persona].values():
        print(FIELDS[count] + ":", info)
        count += 1

#Crea las listas de ausentes (del bus) y agregados (al bus). Los viajeros son automaticamente agregados a la lista de ausentes
AUSENTES = []
AGREGADOS = []
for viajero in DATABASE.keys():
    AUSENTES.append(viajero)

#Agrega a alguien a la lista de agregados. Esta persona esta entrando al bus
def agregarLista():

    while True:

        nuevo = input('Quien esta en el bus? ')

        if not nuevo.isdigit():
            if nuevo == 'q':
                break
            print('No ingresaste un numero! Intentalo de nuevo')
            continue

        if nuevo in DATABASE.keys():
            if nuevo in AUSENTES:
                AUSENTES.remove(nuevo)
                AGREGADOS.append(nuevo)
                print(DATABASE[nuevo]['name'], 'llego al bus!')
            else:
                print(DATABASE[nuevo]['name'], 'ya estaba en el bus!')
        else:
            print('El id', nuevo, 'no esta registrado!')

#Regresa la lista de las personas ausentes al bus
def verAusentes():
    for viajero in AUSENTES:
        print(DATABASE[viajero]['name'])

#Regresa la lista de las personas en el bus
def verAgregados():
    for viajero in AGREGADOS:
        print(DATABASE[viajero]['name'])

#Si una persona decide salir del bus mientras se realiza un conteo, se puede cambiar a la persona de la lista de agregados a la lista de ausentes.
def quitarLista():

    while True:

        nuevo = input('A quien vas a dejar salir del bus? ')

        if not nuevo.isdigit():
            if nuevo == 'q':
                break
            print('No ingresaste un numero! Intentalo de nuevo')
            continue

        if nuevo in DATABASE.keys():
            if nuevo in AGREGADOS:
                AGREGADOS.remove(nuevo)
                AUSENTES.append(nuevo)
                print(DATABASE[nuevo]['name'], 'volvio a salir del bus!')
            else:
                print(DATABASE[nuevo]['name'], 'no esta en el bus todavia!')
        else:
            print('El id', nuevo, 'no esta registrado!')

#Limpia la lista de ausentes para empezar un nuevo conteo
def borrarLista():
    AUSENTES.clear()
    for viajero in DATABASE.keys():
        AUSENTES.append(viajero)
    AGREGADOS.clear()
    print("Lista Reseteada!")



#Opciones para el usuario dentro del contador
def contador():

    while True:
        accion = input('Que quieres hacer? (Agregar, Quitar, Ver Ausentes, Ver Agregados, Reset) ')
        if accion == 'q':
            break
        elif accion in CONTADOR.keys():
            CONTADOR[accion]()
        else:
            "Accion invalida"


def asignarManilla():
    pass



def main():
    while True:
        accion = input('Que quieres hacer? (Buscar, Contador, Administrar NFC) ')
        if accion == 'q':
            break
        elif accion in MENUPRINCIPAL.keys():
            MENUPRINCIPAL[accion]()
        else:
            "Accion invalida"


if __name__ == '__main__':
    main()
