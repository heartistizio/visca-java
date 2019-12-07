# V01

## Configuration

Set `agh.alex.multi.Port` to whatever port is being used by `VISCA`.

## Format of commands

Visca Java command format in the following format:
`CMD|quit|OUT`

where:

- `CMD` - is one of following commands:

  - `SET (address) (new_address)`
  - `ADDRESS`
  - `HOME (address)`
  - `DOWN (address) (speed pan)? (speed tilt)?`
  - `UP (address) (speed pan)? (speed tilt)?`
  - `RIGHT (address) (speed pan)? (speed tilt)?`
  - `LEFT (address) (speed pan)? (speed tilt)?`
  - `TOPRIGHT (address) (speed pan)? (speed tilt)?`
  - `TOPLEFT (address) (speed pan)? (speed tilt)?`
  - `TELEZOOM`
  - `WIDEZOOM`
    `adress` and `new_adress` being integers, `speed pan` and `speed tilt` integers betwen 0-35

- `quit` - quits application
- `OUT` - prints out logs

## How to run

1. Make sure camera is ON
2. Configure port
3. Launch CLI(`Main`) or Web(`App`) application
4. Start by sending an `ADDRESS` command which should set the address to 1 or the next free address.
5. Send commands to camera

## Web server

There's one endpoint `http:localhost:8080/control`.

1. `GET` on that endpoint returns an array of log strings.
2. `POST` send plan string command in the body
