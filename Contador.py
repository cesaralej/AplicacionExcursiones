import csv


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


with open('database.csv') as csv_file:
    csv_reader = csv.reader(csv_file, delimiter=',')
    next(csv_reader)
    for line in csv_reader:
        DATABASE[str(line[0])] = {'id': line[0], 'name': line[1], 'lastName': line[2], 'phone': line[3], 'email': line[4], 'day': line[5], 'month': line[6], 'year': line[7], 'school': line[8], 'insuranceCode': line[9], 'bloodType': line[10], 'passports': line[11], 'passportNumbers': line[12], 'destination': line[13], 'fatherName': line[14], 'fatherPhone': line[15], 'motherName': line[16], 'motherPhone': line[17]}
        # line[0] = Viajero(line[0], line[1], line[2], line[3], line[4], line[5], line[6], line[7], line[8], line[9], line[10], line[11], line[12], line[13], line[14], line[15], line[16], line[17])


def buscar():
    persona = input('A quien quieres busar? ')
    count = 0
    for info in DATABASE[persona].values():
        print(FIELDS[count] + ":", info)
        count += 1


AUSENTES = []
AGREGADOS = []
for viajero in DATABASE.keys():
    AUSENTES.append(viajero)


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


def verAusentes():
    for viajero in AUSENTES:
        print(DATABASE[viajero]['name'])


def verAgregados():
    for viajero in AGREGADOS:
        print(DATABASE[viajero]['name'])


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


def borrarLista():
    AUSENTES.clear()
    for viajero in DATABASE.keys():
        AUSENTES.append(viajero)
    AGREGADOS.clear()


CONTADOR = {'vau': verAusentes, 'vag': verAgregados, 'a': agregarLista, 'r': quitarLista, 'b': borrarLista}


def contador():

    while True:
        accion = input('Que quieres hacer? ')
        if accion == 'q':
            break
        elif accion in CONTADOR.keys():
            CONTADOR[accion]()
        else:
            "Accion invalida"


def asignarManilla():
    pass


MENUPRINCIPAL = {'b': buscar, 'c': contador}


def main():
    while True:
        accion = input('Que quieres hacer? ')
        if accion == 'q':
            break
        elif accion in MENUPRINCIPAL.keys():
            MENUPRINCIPAL[accion]()
        else:
            "Accion invalida"


if __name__ == '__main__':
    main()
